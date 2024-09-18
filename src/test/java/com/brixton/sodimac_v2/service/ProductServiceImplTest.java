package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.data.repository.CategoryRepository;
import com.brixton.sodimac_v2.data.repository.ProductRepository;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
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

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        //Act - Ejecutar el escenario de la prueba
        ProductResponseDTO resultado = productService.createProduct(x);
        //Assert - Verificar el resultado del escenario
        assertEquals(expected.getCategory(), resultado.getCategory());
    }

    @Test
    void createWithList() {

        Category category1 = new Category();
        category1.setId((byte) 1);
        category1.setCategoryName("Category1");

        Category category2 = new Category();
        category2.setId((byte) 2);
        category2.setCategoryName("Category2");

        ProductRequestDTO productRequest1 = new ProductRequestDTO();
        productRequest1.setNameProduct("product1");
        productRequest1.setCategory("1");

        ProductRequestDTO productRequest2 = new ProductRequestDTO();
        productRequest2.setNameProduct("product2");
        productRequest2.setCategory("2");

        List<ProductRequestDTO> inputProducts = Arrays.asList(productRequest1, productRequest2);

        ProductResponseDTO productResponse1 = new ProductResponseDTO();
        productResponse1.setNameProduct("product1");
        productResponse1.setCategory("1");

        ProductResponseDTO productResponse2 = new ProductResponseDTO();
        productResponse2.setNameProduct("product2");
        productResponse2.setCategory("2");

        List<ProductResponseDTO> expectedResponses = Arrays.asList(productResponse1, productResponse2);

        when(categoryRepository.findById((byte) 1)).thenReturn(Optional.of(category1));
        when(categoryRepository.findById((byte) 2)).thenReturn(Optional.of(category2));

        when(productService.createProduct(productRequest1)).thenReturn(productResponse1);
        when(productService.createProduct(productRequest2)).thenReturn(productResponse2);

        // Act
        List<ProductResponseDTO> actualResponses = productService.createWithList(inputProducts);

        // Assert
        assertNotNull(actualResponses);
        assertEquals(expectedResponses.size(), actualResponses.size());

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