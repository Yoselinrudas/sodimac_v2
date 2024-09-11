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
        ProductResponseDTO product = productService.createProduct(inputProduct);
        return ResponseEntity.ok(product);
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
