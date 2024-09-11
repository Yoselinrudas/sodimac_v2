package com.brixton.sodimac_v2.controller;

import com.brixton.sodimac_v2.dto.request.LegalClientRequestDTO;
import com.brixton.sodimac_v2.dto.request.NaturalClientRequestDTO;
import com.brixton.sodimac_v2.dto.response.LegalClientResponseDTO;
import com.brixton.sodimac_v2.dto.response.NaturalClientResponseDTO;
import com.brixton.sodimac_v2.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/management/client")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/natural")
    public ResponseEntity<NaturalClientResponseDTO> createNaturalClient(@Valid @RequestBody NaturalClientRequestDTO inputClient){
        NaturalClientResponseDTO client = clientService.createNaturalClient(inputClient);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/legal")
    public ResponseEntity<LegalClientResponseDTO> createLegalClient(@Valid @RequestBody LegalClientRequestDTO inputClient) {
        LegalClientResponseDTO client = clientService.createLegalClient(inputClient);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/naturals")
    public ResponseEntity<List<NaturalClientResponseDTO>> createWithListNaturalClient(@RequestBody List<NaturalClientRequestDTO> clients) {
        List<NaturalClientResponseDTO> listClients = clientService.createWithListNaturalClient(clients);
        return ResponseEntity.ok(listClients);
    }

    @PostMapping("/legals")
    public ResponseEntity<List<LegalClientResponseDTO>> createWithListLegalClient(@RequestBody List<LegalClientRequestDTO> clients) {
       List<LegalClientResponseDTO> listClients = clientService.createWithListLegalClient(clients);
       return ResponseEntity.ok(listClients);
    }

    @PutMapping("/natural/{numberDoc}")
    public ResponseEntity<NaturalClientResponseDTO> updateNaturalClient(@Valid @PathVariable String numberDoc, @RequestBody NaturalClientRequestDTO client) {
        return ResponseEntity.ok(clientService.updateNaturalClient(numberDoc,client));
    }

    @PutMapping("/legal/{ruc}")
    public ResponseEntity<LegalClientResponseDTO> updateLegalClient(@Valid @PathVariable String ruc, @RequestBody LegalClientRequestDTO client) {
        return ResponseEntity.ok(clientService.updateLegalClient(ruc, client));
    }

    @GetMapping("/natural/{numberDoc}")
    public ResponseEntity<NaturalClientResponseDTO> getNaturalClient(@PathVariable String numberDoc) {
        return ResponseEntity.ok(clientService.getNaturalClient(numberDoc));
    }

    @GetMapping("/legal/{ruc}")
    public ResponseEntity<LegalClientResponseDTO> getLegalClient(@PathVariable String ruc) {
        return ResponseEntity.ok(clientService.getLegalClient(ruc));
    }

    @GetMapping("/naturalClients")
    public ResponseEntity<List<NaturalClientResponseDTO>> getListNaturalClients() {
        return new ResponseEntity<>(clientService.getListNaturalClients(), HttpStatus.OK);
    }

    @GetMapping("/legalClients")
    public ResponseEntity<List<LegalClientResponseDTO>> getListLegalClients() {
        return new ResponseEntity<>(clientService.getListLegalClients(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteNatural/{numberDoc}")
   ResponseEntity<?> deleteNaturalClient(@PathVariable String numberDoc) {
        clientService.deleteNaturalClient(numberDoc);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deleteLegal/{ruc}")
    ResponseEntity<?> deleteLegalClient(@PathVariable String ruc) {
        clientService.deleteLegalClient(ruc);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
