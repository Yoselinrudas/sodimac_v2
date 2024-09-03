package com.brixton.sodimac_v2.service;


import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.data.model.Product;
import com.brixton.sodimac_v2.data.repository.CategoryRepository;
import com.brixton.sodimac_v2.data.repository.ProductRepository;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import com.brixton.sodimac_v2.service.mapper.ProductMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ToString
public class ProductServiceImpl implements ProductService{

    private static final String USER_APP = "BRIXTON";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    public ProductServiceImpl(){

    }


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO inputProduct) {
        Product product = ProductMapper.INSTANCE.ProductRequestDtoToProduct(inputProduct);
        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy(USER_APP);
        //product.setUpdatedAt(LocalDateTime.now());
        //product.setUpdatedBy(USER_APP);
        product.setRegistryState(RegistryStateType.ACTIVE);
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new GenericNotFoundException("Categoria con Id no existente"));
        product.getCategory().setCategoryName(category.getCategoryName());
        log.info("product::: {}", product);
        productRepository.save(product);

        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.productToProductResponseDto(product);
        log.info("ProductResponseDTO: {}", productResponseDTO);
        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> createWithList(List<ProductRequestDTO> inputProducts) {
        List<ProductResponseDTO> outputProduct = new ArrayList<>();
        for(ProductRequestDTO productRequestDTO: inputProducts){
            try{
                outputProduct.add(createProduct(productRequestDTO));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return outputProduct;
    }

    @Override
    public ProductResponseDTO getProduct(long id) {

        Product productFound = productRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Producto con Id no existente"));
        return ProductMapper.INSTANCE.productToProductResponseDto(productFound);
    }

    @Override
    public ProductResponseDTO updateProduct(long id, UpdateProductRequestDTO product) {

        Product original = productRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Producto por id no existente"));

        Product productTemp = ProductMapper.INSTANCE.updateProductRequestDtoToProduct(product);
        original.setName(productTemp.getName());
        original.setCategory(productTemp.getCategory());
        original.setQuantity(productTemp.getQuantity());
        original.setMinQuantity(productTemp.getMinQuantity());
        original.setPriceSupplier(productTemp.getPriceSupplier());
        original.setPriceSale(productTemp.getPriceSale());
        original.setUpdatedAt(LocalDateTime.now());
        original.setUpdatedBy(USER_APP);
        Category category = categoryRepository.findById(productTemp.getCategory().getId()).orElseThrow(() -> new GenericNotFoundException("Category con Id no existente"));
        productTemp.getCategory().setCategoryName(category.getCategoryName());
        original.setCategory(productTemp.getCategory());
        productRepository.save(original);
        return ProductMapper.INSTANCE.productToProductResponseDto(original);
    }

    @Override
    public void deleteProduct(long id) {

        Product productFound =  productRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Producto con Id no existente"));
        productFound.setRegistryState(RegistryStateType.INACTIVE);
        productFound.setUpdatedAt(LocalDateTime.now());
        productFound.setUpdatedBy(USER_APP);
        productRepository.save(productFound);
    }

    @Override
    public List<ProductResponseDTO> getActiveProducts() {
        List<ProductResponseDTO> activeProduct = new ArrayList<>();
        List<Product> productFounds = productRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for(Product productFound: productFounds){
            activeProduct.add(ProductMapper.INSTANCE.productToProductResponseDto(productFound));
        }
        return activeProduct;
    }

    @Override
    public List<ProductResponseDTO> getListProduct(Category category) {
        List<ProductResponseDTO> productFounds = new ArrayList<>();
        List<Product> products = productRepository.findByCategory(category);
        for(Product productFound: products){
            productFounds.add(ProductMapper.INSTANCE.productToProductResponseDto(productFound));
        }
        return productFounds;
    }
}
