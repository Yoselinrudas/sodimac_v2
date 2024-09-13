package com.brixton.sodimac_v2.controller;

import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.dto.request.ProductRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateProductRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProductResponseDTO;
import com.brixton.sodimac_v2.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/management/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO inputProduct){
        log.info("llamando a createProduct de ProductController");
        ProductResponseDTO product = productService.createProduct(inputProduct);
        log.info("paso previo al return en productController");
        return ResponseEntity.ok(product);
    }

    public Integer sumar(Integer x, Integer y) throws NullPointerException {
        //Casos de prueba:
        // 2. x = 50 (5), y = 100(10) -> (x+y) (5+10)=15
        // 1. x = 2 (4), y = 2(8) -> (x+y) (4+8)=12
        // 3. x = 2 (4), y = 50(8) -> (x+y) (4+8)=12
        // 4. x = 50, y = 100(10) -> (x+y) (5+10)=15
        try {
            if (x == null || y == null) {
                throw new NullPointerException("Input values cannot be null");
            }
            int m = Integer.parseInt(x.toString());
            int n = Integer.parseInt(y.toString());
            if(m >= 50 ) {
                m = 5;
            } else {
                m = 4;
            }
            if(n >= 100 ) {
                n = 10;
            } else {
                n = 7;
            }
            return m + n;
        } catch (NullPointerException | NumberFormatException e) {
            log.error("Error en la suma", e);
            return 0;
        }
    }

    @PostMapping("/createWithList")
    public ResponseEntity<List<ProductResponseDTO>> createWithList(@RequestBody List<ProductRequestDTO> inputProducts){

        List<ProductResponseDTO> products = productService.createWithList(inputProducts);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @PathVariable long id, @RequestBody UpdateProductRequestDTO product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/activeProducts")
    public ResponseEntity<List<ProductResponseDTO>> getActiveProducts(){
        return new ResponseEntity<>(productService.getActiveProducts(), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getListProduct(@PathVariable Category category){
        return new ResponseEntity<>(productService.getListProduct(category), HttpStatus.OK);
    }
}
