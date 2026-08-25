package com.idd.product.service;

import com.idd.product.dto.CategoryCreateRequest;
import com.idd.product.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreateRequest request);
    CategoryResponse getCategory(Long id);
    List<CategoryResponse>getAllCategories();
}
