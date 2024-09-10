package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.LegalClient;
import com.brixton.sodimac_v2.data.model.NaturalClient;
import com.brixton.sodimac_v2.data.repository.LegalClientRepository;
import com.brixton.sodimac_v2.data.repository.NaturalClientRepository;
import com.brixton.sodimac_v2.dto.request.LegalClientRequestDTO;
import com.brixton.sodimac_v2.dto.request.NaturalClientRequestDTO;
import com.brixton.sodimac_v2.dto.response.LegalClientResponseDTO;
import com.brixton.sodimac_v2.dto.response.NaturalClientResponseDTO;
import com.brixton.sodimac_v2.service.mapper.ClientMapper;
import com.brixton.sodimac_v2.service.utils.ConstanteError;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.brixton.sodimac_v2.service.utils.FuntionalBusinessInterfaces.auditCreation;
import static com.brixton.sodimac_v2.service.utils.FuntionalBusinessInterfaces.auditUpdate;

@Service
@Slf4j
@ToString
public class ClientServiceImpl implements ClientService{


    @Autowired
    private NaturalClientRepository naturalClientRepository;
    @Autowired
    private LegalClientRepository legalClientRepository;
    
    @Override
    public NaturalClientResponseDTO createNaturalClient(NaturalClientRequestDTO inputClient) {
        NaturalClient client = ClientMapper.INSTANCE.naturalClientRequestDtoToNaturalClient(inputClient);
        client.setDocumentNumber(inputClient.getNumber());
        auditCreation.accept(client);
        log.info(client.toString());
        naturalClientRepository.save(client);
        NaturalClientResponseDTO naturalClientResponseDTO = ClientMapper.INSTANCE.naturalClientToNaturalClientResponseDto(client);
        log.info("NaturalClient: {}", naturalClientResponseDTO);
        return naturalClientResponseDTO;
    }

    @Override
    public LegalClientResponseDTO createLegalClient(LegalClientRequestDTO inputClient) {
        LegalClient client = ClientMapper.INSTANCE.legalClientRequestDtoToLegalClient(inputClient);
        auditCreation.accept(client);
        legalClientRepository.save(client);
        LegalClientResponseDTO legalClientResponseDTO = ClientMapper.INSTANCE.legalClientToLegalClientResponseDto(client);
        log.info("LegalClient: {}", legalClientResponseDTO);
        return legalClientResponseDTO;
    }

    @Override
    public List<NaturalClientResponseDTO> createWithListNaturalClient(List<NaturalClientRequestDTO> clients) {
        List<NaturalClientResponseDTO> outputClients = new ArrayList<>();
        for(NaturalClientRequestDTO naturalClientRequestDTO: clients){
            try{
                outputClients.add(createNaturalClient(naturalClientRequestDTO));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return outputClients;
    }

    @Override
    public List<LegalClientResponseDTO> createWithListLegalClient(List<LegalClientRequestDTO> clients) {
        List<LegalClientResponseDTO> outputClients = new ArrayList<>();
        for(LegalClientRequestDTO legalClientRequestDTO: clients){
            try {
                outputClients.add(createLegalClient(legalClientRequestDTO));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return outputClients;
    }

    @Override
    public NaturalClientResponseDTO updateNaturalClient(String numberDoc, NaturalClientRequestDTO client) {
        NaturalClient original = naturalClientRepository.findById(numberDoc).orElseThrow(() -> new GenericNotFoundException(ConstanteError.CLIENT_NOT_FOUND));
        NaturalClient clientTemp = ClientMapper.INSTANCE.naturalClientRequestDtoToNaturalClient(client);

        original.setTypeDocument(clientTemp.getTypeDocument());
        original.setName(clientTemp.getName());
        original.setLastName(clientTemp.getLastName());
        original.setAddress(clientTemp.getAddress());
        original.setPhone(clientTemp.getPhone());
        original.setEmail(clientTemp.getEmail());
        auditUpdate.accept(original);
        naturalClientRepository.save(original);
        return ClientMapper.INSTANCE.naturalClientToNaturalClientResponseDto(original);
    }

    @Override
    public LegalClientResponseDTO updateLegalClient(String ruc, LegalClientRequestDTO client) {
        LegalClient original = legalClientRepository.findById(ruc).orElseThrow(()-> new GenericNotFoundException("Cliente con ruc no identificaco"));
        LegalClient clientTemp = ClientMapper.INSTANCE.legalClientRequestDtoToLegalClient(client);

        original.setRazonSocial(clientTemp.getRazonSocial());
        original.setAddress(clientTemp.getAddress());
        original.setPhone(clientTemp.getPhone());
        original.setEmail(clientTemp.getEmail());
        original.setSupplier(clientTemp.isSupplier());
        auditUpdate.accept(original);
        legalClientRepository.save(original);
        return ClientMapper.INSTANCE.legalClientToLegalClientResponseDto(original);
    }

    @Override
    public NaturalClientResponseDTO getNaturalClient(String numberDoc) {
        NaturalClient clientFound = naturalClientRepository.findById(numberDoc).orElseThrow(()-> new GenericNotFoundException(ConstanteError.CLIENT_NOT_FOUND));
        return ClientMapper.INSTANCE.naturalClientToNaturalClientResponseDto(clientFound);
    }

    @Override
    public LegalClientResponseDTO getLegalClient(String ruc) {
        LegalClient clientFound = legalClientRepository.findById(ruc).orElseThrow(()-> new GenericNotFoundException("Cliente con ruc no identificado"));
        return ClientMapper.INSTANCE.legalClientToLegalClientResponseDto(clientFound);
    }

    @Override
    public List<NaturalClientResponseDTO> getListNaturalClients() {
        List<NaturalClientResponseDTO> activeClients = new ArrayList<>();
        List<NaturalClient> clientFounds = naturalClientRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for(NaturalClient client : clientFounds){
            activeClients.add(ClientMapper.INSTANCE.naturalClientToNaturalClientResponseDto(client));
        }
        return activeClients;
    }

    @Override
    public List<LegalClientResponseDTO> getListLegalClients() {
        List<LegalClientResponseDTO> activeClients = new ArrayList<>();
        List<LegalClient> clientFounds = legalClientRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for(LegalClient client: clientFounds){
            activeClients.add(ClientMapper.INSTANCE.legalClientToLegalClientResponseDto(client));
        }
        return activeClients;
    }

    @Override
    public void deleteNaturalClient(String numberDoc) {
        NaturalClient clientFound = naturalClientRepository.findById(numberDoc).orElseThrow(()-> new GenericNotFoundException(ConstanteError.CLIENT_NOT_FOUND));
        clientFound.setRegistryState(RegistryStateType.INACTIVE);
        auditUpdate.accept(clientFound);
        naturalClientRepository.save(clientFound);
    }

    @Override
    public void deleteLegalClient(String ruc) {
        LegalClient clientFound = legalClientRepository.findById(ruc).orElseThrow(()-> new GenericNotFoundException("Cliente con ruc no identificado"));
        clientFound.setRegistryState(RegistryStateType.INACTIVE);
        auditUpdate.accept(clientFound);
        legalClientRepository.save(clientFound);
    }
}
