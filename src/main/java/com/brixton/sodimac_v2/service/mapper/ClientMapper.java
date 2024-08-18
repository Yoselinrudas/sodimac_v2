package com.brixton.sodimac_v2.service.mapper;

import com.brixton.sodimac_v2.data.model.LegalClient;
import com.brixton.sodimac_v2.data.model.NaturalClient;
import com.brixton.sodimac_v2.dto.request.LegalClientRequestDTO;
import com.brixton.sodimac_v2.dto.request.NaturalClientRequestDTO;
import com.brixton.sodimac_v2.dto.response.LegalClientResponseDTO;
import com.brixton.sodimac_v2.dto.response.NaturalClientResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "document", target = "typeDocument")
    @Mapping(source = "number", target = "documentNumber")
    NaturalClient naturalClientRequestDtoToNaturalClient(NaturalClientRequestDTO naturalClientRequestDTO);

    @Mapping(source = "typeDocument", target = "document")
    @Mapping(source = "documentNumber", target = "number")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    NaturalClientResponseDTO naturalClientToNaturalClientResponseDto(NaturalClient naturalClient);

    LegalClient legalClientRequestDtoToLegalClient(LegalClientRequestDTO legalClientRequestDTO);
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    LegalClientResponseDTO legalClientToLegalClientResponseDto(LegalClient legalClient);

}
