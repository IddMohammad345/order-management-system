package com.idd.product.service;

import com.idd.product.dto.ProductCreateRequest;
import com.idd.product.dto.ProductResponse;
import com.idd.product.exception.CategoryNotFoundException;
import com.idd.product.exception.ProductNotFoundException;
import com.idd.product.model.Category;
import com.idd.product.model.Product;
import com.idd.product.repository.CategoryRepository;
import com.idd.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        if (productRepository.existsBySku(request.sku())){
            throw new IllegalArgumentException("SKU already exists: "+request.sku());
        }
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found: " + request.categoryId())
        );

        Product product = Product.builder()
                .category(category)
                .name(request.name())
                .description(request.description())
                .sku(request.sku())
                .price(request.price())
                .status("ACTIVE")
                .build();

        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product Not Found: " + id)
                );
        return toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> productResponses = productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return productResponses;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStatus(),
                product.getCreatedAt()
        );
    }
}
