package com.idd.product.service;

import com.idd.product.dto.CategoryCreateRequest;
import com.idd.product.dto.CategoryResponse;
import com.idd.product.exception.CategoryNotFoundException;
import com.idd.product.model.Category;
import com.idd.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())){
            throw new IllegalArgumentException(
                    "Category already exists: "+ request.name()
            );
        }
        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .status("ACTIVE")
                .build();
        return toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        return toResponse(
                categoryRepository.findById(id)
                .orElseThrow(
                        ()->new CategoryNotFoundException(
                                "Category Not Found: "+id
                        )
                )
        );
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getStatus(),
                category.getCreatedAt()
        );
    }
}
