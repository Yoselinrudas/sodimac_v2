package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.model.*;
import com.brixton.sodimac_v2.data.repository.*;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProformaRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.service.mapper.SaleMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        //Obtiene empleado aociado
        Employee employee = employeeRepository.getReferenceById(proforma.getEmployee().getId());

        //estados de relacion con proforma
        //List<StatusSale> statusProformas = statusSaleRepository.findByStatusGroup(StatusGroupType.PROFORMA.name());
        //log.info(statusProformas.toString());

        //Iniciar la lista de detalles de la proforma y total
        List<SaleDetail> details = new ArrayList<>();
        float totalSum = 0;
        for(SaleDetail detail: proforma.getDetails()){
            Product product = productRepository.getReferenceById(detail.getProduct().getId());

            //calcular la cantidad  disponible
            double availableQuantity = product.getQuantity() - getConfirmedQuantityForProduct(product.getId()) - detail.getQuantity();
            //crea nuevo detalle de venta
            SaleDetail proformaDetail = new SaleDetail();
            proformaDetail.setProduct(product);
            proformaDetail.setQuantity(detail.getQuantity());
            proformaDetail.setPriceSale(product.getPriceSale());
            proformaDetail.setTotal(product.getPriceSale() * detail.getQuantity());

            //Verifica la disponibilidad del producto y establece el estado detalle
            List<StatusSale> statusDetails = statusSaleRepository.findByStatusGroup(StatusGroupType.DETAIL.name());
            if(availableQuantity >= 0){
              StatusSale availableStatus = statusDetails.stream()
                      .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("AVAILABLE"))
                      .findFirst()
                      .orElseThrow(() -> new IllegalArgumentException(("Status 'AVAILABLE' not found")));
              proformaDetail.setStatusSale(availableStatus);
              totalSum += proformaDetail.getTotal();
            }else{
                StatusSale outOfStockStatus = statusDetails.stream()
                        .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("OUT_OF_STOCK"))
                        .findFirst()
                        .orElseThrow(()-> new IllegalArgumentException("Status 'OUT_OF_STOCK' not found"));
                proformaDetail.setStatusSale(outOfStockStatus);
            }
            details.add(proformaDetail);
        }

        proforma.setDetails(details);
        proforma.setTotal(totalSum);
        proforma.setEmployee(employee);

        proformaRepository.save(proforma);

        ProformaResponseDTO output = SaleMapper.INSTANCE.proformaToProformaResponseDto(proforma);

        return output;
    }
    private double getConfirmedQuantityForProduct(long productId) {
        List<StatusSale> statusProformas = statusSaleRepository.findByStatusGroup(StatusGroupType.PROFORMA.name());
        double confirmedQuantity = 0;

        // Filtrar las proformas confirmadas
        StatusSale confirmedStatus = statusProformas.stream()
                .filter(status->status.getDescription().equals("CONFIRMED"))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Status 'CONFIRMED' not found"));

        List<Proforma> confirmedProformas = proformaRepository.findAllByStatusSale(confirmedStatus);

        // Sumar las cantidades confirmadas para el producto
        for(Proforma proforma : confirmedProformas){
            for(SaleDetail detail : proforma.getDetails()){
                if(detail.getProduct().getId() == productId){
                    confirmedQuantity += detail.getQuantity();
                }
            }
        }
        return confirmedQuantity;
    }

    @Override
    public ProformaResponseDTO getProforma(long id, ProformaRequestDTO proforma) {
        return null;
    }

    @Override
    public ProformaResponseDTO updateProforma(long id, UpdateProformaRequestDTO proformaDTO) {
        return null;
    }
}
