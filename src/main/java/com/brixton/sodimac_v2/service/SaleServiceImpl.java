package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
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

    List<StatusSale> statusProformas = statusSaleRepository.findByStatusGroup(StatusGroupType.PROFORMA);

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
        Employee employee = employeeRepository.findById(proforma.getEmployee().getId()).orElseThrow(() -> new GenericNotFoundException(("Employee con Id no existente")));

        //Iniciar la lista de detalles de la proforma y total
        List<SaleDetail> details = new ArrayList<>();
        log.info("employee: {}", employee);
        log.info("proforma: {}", proforma);
        float totalSum = 0;
        for(SaleDetail detail: proforma.getDetails()){
            log.info("product: ");
            Product product = productRepository.findById(detail.getProduct().getId()).orElseThrow(() -> new GenericNotFoundException(("Product con Id no existente")));;
            log.info("product: {}", product);
            //calcular la cantidad  disponible
            double availableQuantity = product.getQuantity() - getConfirmedQuantityForProduct(product.getId()) - detail.getQuantity();
            //crea nuevo detalle de venta
            SaleDetail proformaDetail = new SaleDetail();
            proformaDetail.setProduct(product);
            proformaDetail.setQuantity(detail.getQuantity());
            proformaDetail.setPriceSale(product.getPriceSale());
            proformaDetail.setTotal(product.getPriceSale() * detail.getQuantity());

            //Verifica la disponibilidad del producto y establece el estado detalle
            log.info(StatusGroupType.DETAIL.name());
            log.info(StatusGroupType.DETAIL.name());
            List<StatusSale> statusDetails = statusSaleRepository.findByStatusGroup(StatusGroupType.DETAIL);
            log.info("cantidad disponible: {}", availableQuantity);
            if(availableQuantity >= 0){
              StatusSale availableStatus = statusDetails.stream()
                      .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("AVAILABLE"))
                      .findFirst()
                      .orElseThrow(() -> new IllegalArgumentException(("Status 'AVAILABLE' not found")));
              log.info("available status:{}", availableStatus);
              proformaDetail.setStatusSale(availableStatus);
              totalSum += proformaDetail.getTotal();
            }else{
                StatusSale outOfStockStatus = statusDetails.stream()
                        .filter(status -> status.getStatusGroup() == StatusGroupType.DETAIL && status.getDescription().equals("OUT_OF_STOCK"))
                        .findFirst()
                        .orElseThrow(()-> new IllegalArgumentException("Status 'OUT_OF_STOCK' not found"));
                log.info("available status:{}", outOfStockStatus);
                proformaDetail.setStatusSale(outOfStockStatus);
            }
            details.add(proformaDetail);
        }

        proforma.setDetails(details);
        proforma.setTotal(totalSum);
        //proforma.setEmployee(employee);

        proformaRepository.save(proforma);

        return SaleMapper.INSTANCE.proformaToProformaResponseDto(proforma);
    }
    private double getConfirmedQuantityForProduct(long productId) {

        // Filtrar las proformas confirmadas
        StatusSale confirmedStatus = statusProformas.stream()
                .filter(status->status.getDescription().equals("CONFIRMED"))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Status 'CONFIRMED' not found"));

        Double confirmedQuantity = proformaRepository.findConfirmedQuantityForProduct(productId, confirmedStatus);

        return confirmedQuantity != null ? confirmedQuantity:0;
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
