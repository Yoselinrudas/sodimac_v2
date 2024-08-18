package com.brixton.sodimac_v2.data.controller;

import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProformaRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.service.SaleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*@GetMapping("/{id}")
    public ResponseEntity<ProformaResponseDTO> getProforma(@PathVariable long id, ProformaRequestDTO proforma);
    //ProformaResponseDTO updateProforma(long id, UpdateProformaRequestDTO proformaDTO);*/
}
