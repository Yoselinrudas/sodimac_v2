package com.brixton.sodimac_v2.controller;

import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import com.brixton.sodimac_v2.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock //Simulacion de la clase ProductService
    ProductService productService;

    @InjectMocks
    static ProductController productController;

    //Dto - Controller - Service - Repository - Entity
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creaProducto() {
        //AAA -> Arrange, Act, Assert
        //Arrange -> Preparar el entorno de la prueba
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        when(productService.createProduct(productRequestDTO)).thenReturn(productResponseDTO);
        //Act -> Actuar sobre el metodo que quiero probar
        ProductResponseDTO response = productController.createProduct(productRequestDTO).getBody();
        //Assert -> Verificar que el metodo se ejecutó correctamente
        assertEquals(productResponseDTO, response);
    }


    @Test
    void sumarTestScenario1(){
        //Arrange
        Integer sumaExpected = 15;

        Integer inputX = 50;
        Integer inputY = 100;
        //Act
        Integer suma = productController.sumar(inputX, inputY);
        //Assert
        assertEquals(sumaExpected, suma);
    }

    @Test
    void sumarTestScenario2(){
        //Arrange
        Integer sumaExpected = 11;

        Integer inputX = 5;
        Integer inputY = 70;
        //Act
        Integer suma = productController.sumar(inputX, inputY);
        //Assert
        assertEquals(sumaExpected, suma);
    }

    //@Test
//    void sumarTestScenario2(){
//        //Arrange
//        Integer sumaExpected = 12;
//
//        Integer inputX = 50;
//        Integer inputY = 2;
//        //Act
//        Integer suma = productController.sumar(inputX, inputY);
//        //Assert
//        assertEquals(sumaExpected, suma);
//    }

    //@Test
//    void sumarTestScenario3(){
//        //Arrange
//        Integer sumaExpected = 14;
//
//        Integer inputX = 2;
//        Integer inputY = 100;
//        //Act
//        Integer suma = productController.sumar(inputX, inputY);
//        //Assert
//        assertEquals(sumaExpected, suma);
//    }

    //@Test
    //@DisplayName("Suma, x mayor a 50 y y menor a 100")
//    void sumarTestScenario4(){
//        //Arrange
//        Integer sumaExpected = 11;
//
//        Integer inputX = 2;
//        Integer inputY = 2;
//        //Act
//        Integer suma = productController.sumar(inputX, inputY);
//        //Assert
//        assertEquals(sumaExpected, suma);
//    }

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