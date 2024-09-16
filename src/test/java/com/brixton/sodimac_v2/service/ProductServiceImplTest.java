package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.controller.ProductController;
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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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
    @Mock
    ProductController productController;

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
        // Crear categoría simulada para evitar que falle la validación en el servicio
        Category category = new Category();
        // Lista de entrada
        List<ProductRequestDTO> inputProducts = new ArrayList<>();

        ProductRequestDTO product1 = new ProductRequestDTO();
        product1.setNameProduct("product1");
        product1.setCategory("1"); // Asignar ID de categoría
        ProductRequestDTO product2 = new ProductRequestDTO();
        product2.setNameProduct("product2");
        product2.setCategory("2"); // Asignar ID de categoría

        inputProducts.add(product1);
        inputProducts.add(product2);

        // Lista de salida esperada
        List<ProductResponseDTO> outputProduct = new ArrayList<>();
        ProductResponseDTO response1 = new ProductResponseDTO();
        response1.setId(1L);
        response1.setCategory("1");
        ProductResponseDTO response2 = new ProductResponseDTO();
        response2.setId(2L);
        response2.setCategory("2");

        outputProduct.add(response1);
        outputProduct.add(response2);

        // Simulación de búsqueda de categoría en el repositorio
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        //when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        when(productService.createWithList(inputProducts)).thenReturn(outputProduct);

        ResponseEntity<List<ProductResponseDTO>> actuals = productController.createWithList(inputProducts);

        assertEquals(outputProduct.size(), actuals.getBody().size());
        assertEquals(outputProduct, actuals.getBody());

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