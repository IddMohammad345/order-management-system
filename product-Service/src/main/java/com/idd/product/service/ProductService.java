package com.idd.product.service;

import com.idd.product.dto.ProductCreateRequest;
import com.idd.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse getProduct(Long id);
    List<ProductResponse>getAllProduct();
}
