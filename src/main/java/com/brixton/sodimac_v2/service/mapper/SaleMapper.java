package com.brixton.sodimac_v2.service.mapper;

import com.brixton.sodimac_v2.data.model.*;
import com.brixton.sodimac_v2.dto.request.BillRequestDTO;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.SaleDetailRequestDTO;
import com.brixton.sodimac_v2.dto.request.TicketRequestDTO;
import com.brixton.sodimac_v2.dto.response.BillResponseDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.dto.response.SaleDetailResponseDTO;
import com.brixton.sodimac_v2.dto.response.TicketResponseDTO;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleMapper {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    @Mapping(source = "employee", target = "employee.id")
    @Mapping(source = "statusSale", target = "statusSale.id")
    Proforma proformaRequestDtoToProforma(ProformaRequestDTO proformaRequestDTO);
    @Mapping(source = "employee.id", target = "employee")
    @Mapping(source = "statusSale.id", target = "statusSale")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    ProformaResponseDTO proformaToProformaResponseDto(Proforma proforma);
    @Mapping(source = "product", target = "product.id")
    SaleDetail saleDetailRequestDtoToSaleDetail(SaleDetailRequestDTO saleDetailRequestDTO);
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "product.id", target = "product")
    @Mapping(source = "statusSale.description", target = "statusDetail")
    SaleDetailResponseDTO saleDetailToSaleDetailResponseDto(SaleDetail saleDetail);

    //@Mapping(source = "proformaId", target = "proforma")
    @Mapping(source = "client",target = "legalClient")
    Bill billRequestDtoToBill(BillRequestDTO billRequestDTO);
    @Mapping(source = "legalClient", target = "client")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    BillResponseDTO billToBillResponseDto(Bill bill);
    //@Mapping(source = "proformaId", target = "proforma")
    @Mapping(source = "client",target = "naturalClient")
    Ticket ticketRequestDtoToTicket(TicketRequestDTO ticketRequestDTO);
    @Mapping(source = "naturalClient", target = "client")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    TicketResponseDTO ticketToTicketResponseDto(Ticket ticket);




}
