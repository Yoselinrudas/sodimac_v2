package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.controller.UnauthorizedException;
import com.brixton.sodimac_v2.data.model.enums.RegistryProformaType;
import com.brixton.sodimac_v2.data.model.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.model.*;
import com.brixton.sodimac_v2.data.repository.*;
import com.brixton.sodimac_v2.dto.request.BillRequestDTO;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.TicketRequestDTO;
import com.brixton.sodimac_v2.dto.response.BillResponseDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.dto.response.SaleDetailResponseDTO;
import com.brixton.sodimac_v2.dto.response.TicketResponseDTO;
import com.brixton.sodimac_v2.service.mapper.ClientMapper;
import com.brixton.sodimac_v2.service.mapper.SaleMapper;
import com.brixton.sodimac_v2.service.utils.Constantes;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.brixton.sodimac_v2.service.utils.FuntionalBusinessInterfaces.auditCreation;
import static com.brixton.sodimac_v2.service.utils.FuntionalBusinessInterfaces.business;

@Service
@Slf4j
@ToString
public class SaleServiceImpl implements SaleService{



    @Autowired
    private ProformaRepository proformaRepository;
    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StatusSaleRepository statusSaleRepository;
    @Autowired
    private NaturalClientRepository naturalClientRepository;
    @Autowired
    private LegalClientRepository legalClientRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private  BillRepository billRepository;

    public SaleServiceImpl(){

    }

    @Override
    public ProformaResponseDTO createProforma(ProformaRequestDTO inputProforma) {

        //Mapea el DO de solicitud a la entidad Proforma
        Proforma proforma = SaleMapper.INSTANCE.proformaRequestDtoToProforma(inputProforma);
        auditCreation.accept(proforma);
        proforma.setRegistryProforma(RegistryProformaType.UNUSED);

        Employee employee = employeeRepository.findById(proforma.getEmployee().getId()).orElseThrow(() -> new GenericNotFoundException(("Employee con Id no existente")));

        if (!"SHELLING".equalsIgnoreCase(employee.getArea().getAreaName())) {
            throw new UnauthorizedException("Solo los empleados del área de ventas pueden crear una proforma");
        }
        //Verifica la disponibilidad del producto y establece el estado detalle
        List<StatusSale> statusDetails = statusSaleRepository.findByStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleProforma = statusSaleRepository.findByIdAndStatusGroup(proforma.getStatusSale().getId(), StatusGroupType.PROFORMA).orElseThrow(() -> new GenericNotFoundException(("findByIdAndStatusGroup no existente")));
        proforma.setStatusSale(statusSaleProforma);
        //Iniciar la lista de detalles de la proforma y total
        List<SaleDetail> detailToSaves = new ArrayList<>();
        List<SaleDetail> detailToResponses = new ArrayList<>();
        log.info("employee: {}", employee);
        log.info("proforma: {}", proforma);
        float totalSum = 0;
        for(SaleDetail detail: proforma.getDetails()){

            Product product = productRepository.findById(detail.getProduct().getId())
                    .orElseThrow(() -> new GenericNotFoundException(("Product con Id no existente")));;

            //calcular la cantidad  disponible
            double availableQuantity = product.getQuantity() - getConfirmedQuantityForProduct(product.getId()) - detail.getQuantity();

            detail.setProduct(product);
            detail.setPriceSale(product.getPriceSale());
            detail.setTotal(product.getPriceSale() * detail.getQuantity());
            detailToResponses.add(detail);

            log.info("cantidad disponible: {}", availableQuantity);
            if(availableQuantity >= 0){
                StatusSale availableStatus = statusDetails.stream()
                        .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("AVAILABLE"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(("Status 'AVAILABLE' not found")));
                log.info("available status:{}", availableStatus);
                detail.setStatusSale(availableStatus);
                totalSum += detail.getTotal();
                detailToSaves.add(detail);
            }else{
                StatusSale outOfStockStatus = statusDetails.stream()
                        .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("OUT_OF_STOCK"))
                        .findFirst()
                        .orElseThrow(()-> new IllegalArgumentException("Status 'OUT_OF_STOCK' not found"));
                log.info("available status:{}", outOfStockStatus);
                detail.setStatusSale(outOfStockStatus);
            }
        }
        proforma.setTotal(totalSum);
       // Filtrar por estado de la proforma
        if ("CONFIRMED".equalsIgnoreCase(proforma.getStatusSale().getDescription())) {
            // Guardar en la base de datos si la proforma está confirmada
            proformaRepository.save(proforma);

            for (SaleDetail detail : detailToSaves) {
                detail.setProforma(proforma);
                saleDetailRepository.save(detail);
            }
            proforma.setDetails(detailToResponses);
        } else {
            // Si la proforma es temporal, solo mostrar los datos
            log.info("La proforma tiene un estado TEMPORAL");
            proforma.setDetails(detailToResponses);
        }

        return SaleMapper.INSTANCE.proformaToProformaResponseDto(proforma);
    }
    private double getConfirmedQuantityForProduct(long productId) {
        List<StatusSale> statusProformas = statusSaleRepository.findByStatusGroup(StatusGroupType.PROFORMA);
        // Filtrar las proformas confirmadas
        StatusSale confirmedStatus = statusProformas.stream()
                .filter(status->status.getDescription().equals("CONFIRMED"))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Status 'CONFIRMED' not found"));

        Double confirmedQuantity = proformaRepository.findConfirmedQuantityForProduct(productId, confirmedStatus, RegistryProformaType.UNUSED);

        return confirmedQuantity != null ? confirmedQuantity:0;
    }

    @Override
    public ProformaResponseDTO getProforma(long id) {
        Proforma proformaFound = proformaRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new GenericNotFoundException("Proforma con Id no existente"));
        return SaleMapper.INSTANCE.proformaToProformaResponseDto(proformaFound);
    }

    @Override
    public TicketResponseDTO confirmSaleTicket(TicketRequestDTO confirmedTicket) {

        Proforma confirmedProforma = proformaRepository.findByIdWithDetails(confirmedTicket.getProformaId())
                .orElseThrow(() -> new GenericNotFoundException("Proforma con Id no existente"));

        // Verificar si la proforma ya ha sido usada
        if (confirmedProforma.getRegistryProforma() == RegistryProformaType.USED) {
            throw new IllegalStateException("La proforma ya ha sido utilizada y no se puede volver a procesar.");
        }

        NaturalClient client = naturalClientRepository.findById(confirmedTicket.getClient().getNumber()).orElseGet(() -> {
            NaturalClient newClient = ClientMapper.INSTANCE.naturalClientRequestDtoToNaturalClient(confirmedTicket.getClient());
        newClient.setDocumentNumber(confirmedTicket.getClient().getNumber());
        auditCreation.accept(newClient);
        return naturalClientRepository.save(newClient);
        });

        Ticket ticket = SaleMapper.INSTANCE.ticketRequestDtoToTicket(confirmedTicket);
        auditCreation.accept(ticket);
        ticket.setNaturalClient(client);
        ticket.setProforma(confirmedProforma);

        ticket = ticketRepository.save(ticket);

        double totalSum = confirmedProforma.getDetails().stream()
                .mapToDouble(SaleDetail::getTotal)
                .sum();

        for(SaleDetail detail: confirmedProforma.getDetails()){
            Product productUpdated = detail.getProduct();
            productUpdated.setQuantity(productUpdated.getQuantity() - detail.getQuantity());
            productRepository.save(productUpdated);
        }
        confirmedProforma.setRegistryProforma(RegistryProformaType.USED);
        proformaRepository.save(confirmedProforma);

        TicketResponseDTO ticketResponseDTO = SaleMapper.INSTANCE.ticketToTicketResponseDto(ticket);

        business.accept(ticketResponseDTO);
        ticketResponseDTO.setTotal(totalSum);
        List<SaleDetailResponseDTO> saleDetailResponses = confirmedProforma.getDetails().stream()
                .map(SaleMapper.INSTANCE::saleDetailToSaleDetailResponseDto)
                .collect(Collectors.toList());

        ticketResponseDTO.setSaleDetails(saleDetailResponses);

        return ticketResponseDTO;
    }

    @Override
    public TicketResponseDTO getTicket(long id) {

        Ticket ticketFound = ticketRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new GenericNotFoundException("Ticket con Id no existente"));
        TicketResponseDTO ticket = SaleMapper.INSTANCE.ticketToTicketResponseDto(ticketFound);
        business.accept(ticket);
        return ticket;
    }

    @Override
    public BillResponseDTO confirmSaleBill(BillRequestDTO confirmedBill) {

        Proforma confirmedProforma = proformaRepository.findByIdWithDetails(confirmedBill.getProformaId()).orElseThrow(() -> new GenericNotFoundException("Proforma con Id no existente"));
        // Verificar si la proforma ya ha sido usada
        if (confirmedProforma.getRegistryProforma() == RegistryProformaType.USED) {
            throw new IllegalStateException("La proforma ya ha sido utilizada y no se puede volver a procesar.");
        }

        LegalClient client = legalClientRepository.findById(confirmedBill.getClient().getRuc())
                .orElseGet(() -> {
                    LegalClient newClient = ClientMapper.INSTANCE.legalClientRequestDtoToLegalClient(confirmedBill.getClient());
                    newClient.setRuc(confirmedBill.getClient().getRuc());
                    auditCreation.accept(newClient);
                    return legalClientRepository.save(newClient);
                });

        Bill bill = SaleMapper.INSTANCE.billRequestDtoToBill(confirmedBill);
        auditCreation.accept(bill);
        bill.setLegalClient(client);
        bill.setProforma(confirmedProforma);

        double subTotal= confirmedProforma.getDetails().stream()
                .mapToDouble(SaleDetail::getTotal)
                .sum();

        double igv = subTotal * Constantes.IGV;
        double total = subTotal + igv;


        bill = billRepository.save(bill);


        for(SaleDetail detail: confirmedProforma.getDetails()){
            Product productUpdated = detail.getProduct();
            productUpdated.setQuantity(productUpdated.getQuantity() - detail.getQuantity());
            productRepository.save(productUpdated);
        }

        confirmedProforma.setRegistryProforma(RegistryProformaType.USED);
        proformaRepository.save(confirmedProforma);

        BillResponseDTO billResponseDTO = SaleMapper.INSTANCE.billToBillResponseDto(bill);
        business.accept(billResponseDTO);

        List<SaleDetailResponseDTO> saleDetailResponses = confirmedProforma.getDetails().stream()
                .map(SaleMapper.INSTANCE::saleDetailToSaleDetailResponseDto)
                .collect(Collectors.toList());
        billResponseDTO.setSalesDetails(saleDetailResponses);
        billResponseDTO.setSubTotal(subTotal);
        billResponseDTO.setTotal(total);
        billResponseDTO.setIgv(igv);
        return billResponseDTO;
    }

    @Override
    public BillResponseDTO getBill(long id) {

        Bill billFound = billRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new GenericNotFoundException("Bill con Id no existente"));
        BillResponseDTO bill = SaleMapper.INSTANCE.billToBillResponseDto(billFound);
        business.accept(bill);
        return bill;
    }


}
