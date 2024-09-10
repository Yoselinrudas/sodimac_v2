package com.brixton.sodimac_v2.service.mapper;


import com.brixton.sodimac_v2.data.model.Product;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "category", target = "category.id")
    @Mapping(source = "nameProduct", target = "name")
    Product productRequestDtoToProduct(ProductRequestDTO productRequestDTO);

    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "name", target = "nameProduct")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    ProductResponseDTO productToProductResponseDto(Product product);

    @Mapping(source = "category", target = "category.id")
    @Mapping(source = "nameProduct", target = "name")
    Product updateProductRequestDtoToProduct(UpdateProductRequestDTO updateProductRequestDTO);
}
