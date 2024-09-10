package com.brixton.sodimac_v2.service.mapper;

import com.brixton.sodimac_v2.data.model.Movement;
import com.brixton.sodimac_v2.dto.request.MovementProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.MovementProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "typeOfMovement", target = "typeOfMovement.id")
    @Mapping(source = "productId", target = "productId.id")
    Movement movementProductRequestDtoToMovement(MovementProductRequestDTO movementProductRequestDTO);
    @Mapping(source = "typeOfMovement.id", target = "typeOfMovement")
    @Mapping(source = "productId.id", target = "productId")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "dateMovement", target = "dateMovement", dateFormat = "yyyy/MM/dd" )
    MovementProductResponseDTO movementToMovementProductResponseDto(Movement movement);

}
