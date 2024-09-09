package com.brixton.sodimac_v2.data.controller;

import com.brixton.sodimac_v2.dto.request.BillRequestDTO;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.TicketRequestDTO;
import com.brixton.sodimac_v2.dto.response.BillResponseDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.dto.response.TicketResponseDTO;
import com.brixton.sodimac_v2.service.SaleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/management/sale")
@Slf4j
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/")
    public ResponseEntity<ProformaResponseDTO> createProforma(@Valid @RequestBody ProformaRequestDTO inputProforma){
        ProformaResponseDTO proforma = saleService.createProforma(inputProforma);
        return ResponseEntity.ok(proforma);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProformaResponseDTO> getProforma(@PathVariable long id){
        return ResponseEntity.ok(saleService.getProforma(id));
    }
    @PostMapping("/confirmTicket")
    public ResponseEntity<TicketResponseDTO> confirmSaleTicket(@RequestBody TicketRequestDTO confirmedTicket){
        TicketResponseDTO ticket = saleService.confirmSaleTicket(confirmedTicket);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable long id){

        return ResponseEntity.ok(saleService.getTicket(id));
    }

    @PostMapping("/confirmBill")
    ResponseEntity<BillResponseDTO> confirmSaleBill(@RequestBody BillRequestDTO confirmedBill){
        BillResponseDTO bill = saleService.confirmSaleBill(confirmedBill);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/bill/{id}")
    ResponseEntity<BillResponseDTO> getBill(@PathVariable long id){

        return ResponseEntity.ok(saleService.getBill(id));
    }

    @DeleteMapping("/ticket/{id}")
    ResponseEntity<TicketResponseDTO> deleteTicket(@PathVariable long id){
        saleService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/bill/{id}")
    ResponseEntity<BillResponseDTO> deleteBill(@PathVariable long id){
        saleService.deleteBill(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
