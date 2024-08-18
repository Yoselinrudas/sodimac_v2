package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProformaRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;

public interface SaleService {
    ProformaResponseDTO createProforma(ProformaRequestDTO inputProforma);
    ProformaResponseDTO getProforma(long id, ProformaRequestDTO proforma);
    ProformaResponseDTO updateProforma(long id, UpdateProformaRequestDTO proformaDTO);
    //Object confirmSaleTicket(TicketRequestDTO confirmedTicket);
    //Object confirmSaleBill(BillRequestDTO confirmedBill);
    //Object getTicket(long id);
    //Object getBill(long id);
}
