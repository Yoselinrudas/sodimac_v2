package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.model.*;
import com.brixton.sodimac_v2.data.repository.*;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.service.mapper.SaleMapper;
import jakarta.transaction.Transactional;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@ToString
public class SaleServiceImpl implements SaleService{

    private  static final String USER_APP = "BRIXTON";

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


    public SaleServiceImpl(){

    }

    @Override
    public ProformaResponseDTO createProforma(ProformaRequestDTO inputProforma) {

        //Mapea el DO de solicitud a la entidad Proforma
        Proforma proforma = SaleMapper.INSTANCE.proformaRequestDtoToProforma(inputProforma);
        proforma.setCreatedAt(LocalDateTime.now());
        proforma.setCreatedBy(USER_APP);
        proforma.setRegistryState(RegistryStateType.ACTIVE);

        Employee employee = employeeRepository.findById(proforma.getEmployee().getId()).orElseThrow(() -> new GenericNotFoundException(("Employee con Id no existente")));
        //Verifica la disponibilidad del producto y establece el estado detalle
        List<StatusSale> statusDetails = statusSaleRepository.findByStatusGroup(StatusGroupType.DETAIL);
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
            //crea nuevo detalle de venta
            //SaleDetail proformaDetail = new SaleDetail();
            detail.setProduct(product);
            //detail.setQuantity(detail.getQuantity());
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
        //proforma.setEmployee(employee);
        proformaRepository.save(proforma);

        for(SaleDetail detail: detailToSaves){
            detail.setProforma(proforma);
            saleDetailRepository.save(detail);
        }
        proforma.setDetails(detailToResponses);
        return SaleMapper.INSTANCE.proformaToProformaResponseDto(proforma);
    }
    private double getConfirmedQuantityForProduct(long productId) {
        List<StatusSale> statusProformas = statusSaleRepository.findByStatusGroup(StatusGroupType.PROFORMA);
        // Filtrar las proformas confirmadas
        StatusSale confirmedStatus = statusProformas.stream()
                .filter(status->status.getDescription().equals("CONFIRMED"))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Status 'CONFIRMED' not found"));

        Double confirmedQuantity = proformaRepository.findConfirmedQuantityForProduct(productId, confirmedStatus);

        return confirmedQuantity != null ? confirmedQuantity:0;
    }

    @Transactional
    @Override
    public ProformaResponseDTO getProforma(long id) {
        Proforma proformaFound = proformaRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Proforma con Id no existente"));
        return SaleMapper.INSTANCE.proformaToProformaResponseDto(proformaFound);
    }

    @Transactional
    @Override
    public ProformaResponseDTO updateProforma(long id, ProformaRequestDTO proformaDTO) {
        Proforma original = proformaRepository.findById(id).orElseThrow(() -> new GenericNotFoundException(("Proforma con Id no existente")));
        Proforma proformaTemp = SaleMapper.INSTANCE.proformaRequestDtoToProforma(proformaDTO);

        original.setUpdatedAt(LocalDateTime.now());
        original.setUpdatedBy(USER_APP);
        original.setEmployee(proformaTemp.getEmployee());
        original.setStatusSale(proformaTemp.getStatusSale());

        //Eliminar detalle de bd
        saleDetailRepository.deleteAll(original.getDetails());
        original.getDetails().clear();

        List<StatusSale> statusDetails = statusSaleRepository.findByStatusGroup(StatusGroupType.DETAIL);
        //Iniciar la lista de detalles de la proforma y total
        List<SaleDetail> detailToSaves = new ArrayList<>();
        List<SaleDetail> detailToResponses = new ArrayList<>();

        float totalSum = 0;
        for(SaleDetail detail: proformaTemp.getDetails()){

            Product product = productRepository.findById(detail.getProduct().getId())
                    .orElseThrow(() -> new GenericNotFoundException(("Product con Id no existente")));;

            //calcular la cantidad  disponible
            double availableQuantity = product.getQuantity() - getConfirmedQuantityForProduct(product.getId()) - detail.getQuantity();
            //crea nuevo detalle de venta
            //SaleDetail proformaDetail = new SaleDetail();
            detail.setProduct(product);
            //detail.setQuantity(detail.getQuantity());
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
        original.setTotal(totalSum);
        //proforma.setEmployee(employee);
        proformaRepository.save(original);

        for(SaleDetail detail: detailToSaves){
            detail.setProforma(original);
            saleDetailRepository.save(detail);
        }
        original.setDetails(detailToResponses);

        proformaRepository.save(original);
        return SaleMapper.INSTANCE.proformaToProformaResponseDto(original);
    }
}
