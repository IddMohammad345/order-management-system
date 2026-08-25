package com.idd.product.controller;

import com.idd.product.dto.ProductCreateRequest;
import com.idd.product.dto.ProductResponse;
import com.idd.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Transactional(readOnly = true)
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse>createProduct(
            @Valid @RequestBody ProductCreateRequest productCreateRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProduct(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse>getAllProducts(){
        return productService.getAllProduct();
    }

}
