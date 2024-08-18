package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.dto.request.LegalClientRequestDTO;
import com.brixton.sodimac_v2.dto.request.NaturalClientRequestDTO;
import com.brixton.sodimac_v2.dto.response.LegalClientResponseDTO;
import com.brixton.sodimac_v2.dto.response.NaturalClientResponseDTO;

import java.util.List;

public interface ClientService {

    NaturalClientResponseDTO createNaturalClient(NaturalClientRequestDTO inputClient);
    LegalClientResponseDTO createLegalClient(LegalClientRequestDTO inputClient);
    List<NaturalClientResponseDTO> createWithListNaturalClient(List<NaturalClientRequestDTO> clients );
    List<LegalClientResponseDTO> createWithListLegalClient(List<LegalClientRequestDTO> clients );
    NaturalClientResponseDTO updateNaturalClient(String numberDoc, NaturalClientRequestDTO client);
    LegalClientResponseDTO  updateLegalClient(String ruc, LegalClientRequestDTO client);
    NaturalClientResponseDTO getNaturalClient(String numberDoc);
    LegalClientResponseDTO getLegalClient(String ruc);
    List<NaturalClientResponseDTO> getListNaturalClients();
    List<LegalClientResponseDTO> getListLegalClients();
    void deleteNaturalClient(String numberDoc);
    void deleteLegalClient(String ruc);

}
