package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO inputProduct);
    List<ProductResponseDTO> createWithList(List<ProductRequestDTO> inputProducts);
    ProductResponseDTO getProduct(long id);
    ProductResponseDTO updateProduct(long id, UpdateProductRequestDTO product);
    void deleteProduct(long id);
    List<ProductResponseDTO> getActiveProducts();
    List<ProductResponseDTO> getListProduct(Category category);

}
