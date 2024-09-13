package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.data.model.Product;
import com.brixton.sodimac_v2.data.repository.CategoryRepository;
import com.brixton.sodimac_v2.data.repository.ProductRepository;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductServiceImpl productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        //Arrange - Definir el escenario de la prueba
        ProductRequestDTO x= new ProductRequestDTO();
        x.setCategory("1");
        Category category = new Category();
        category.setCategoryName("CATEGORIX");

        ProductResponseDTO expected = new ProductResponseDTO();
        expected.setCategory("1");

        //when(productRepository.save(any())).thenReturn(new Product());
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        //Act - Ejecutar el escenario de la prueba
        ProductResponseDTO resultado = productService.createProduct(x);
        //Assert - Verificar el resultado del escenario
        assertEquals(expected.getCategory(), resultado.getCategory());
    }

    @Test
    void createWithList() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getActiveProducts() {
    }

    @Test
    void getListProduct() {
    }
}