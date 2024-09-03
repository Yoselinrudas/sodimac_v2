package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.dto.request.BillRequestDTO;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.TicketRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProformaRequestDTO;
import com.brixton.sodimac_v2.dto.response.BillResponseDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.dto.response.TicketResponseDTO;

public interface SaleService {
    ProformaResponseDTO createProforma(ProformaRequestDTO inputProforma);
    ProformaResponseDTO getProforma(long id);
    //ProformaResponseDTO updateProforma(long id, ProformaRequestDTO proformaDTO);
    TicketResponseDTO confirmSaleTicket(TicketRequestDTO confirmedTicket);
    TicketResponseDTO getTicket(long id);
    BillResponseDTO confirmSaleBill(BillRequestDTO confirmedBill);
    BillResponseDTO getBill(long id);
}
