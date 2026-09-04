package com.idd.inventoryservice.service;

import com.idd.inventoryservice.dto.InventoryCreateRequest;
import com.idd.inventoryservice.dto.InventoryResponse;
import com.idd.inventoryservice.dto.StockReserveRequest;
import com.idd.inventoryservice.dto.StockResponse;
import com.idd.inventoryservice.entity.Inventory;
import com.idd.inventoryservice.exception.InventoryNotFoundException;
import com.idd.inventoryservice.repository.InventoryRepository;
import com.idd.inventoryservice.repository.InventoryTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    @Override
    public InventoryResponse createInventory(InventoryCreateRequest inventoryCreateRequest) {
            inventoryRepository.findByProductId(inventoryCreateRequest.productId())
                    .ifPresent(
                            inventory-> {
                                throw new IllegalArgumentException(
                                        "Inventory already exist for the product: "
                                        +inventoryCreateRequest.productId()
                                );
                            }
                            );
        Inventory inventory = Inventory.builder()
                .productId(inventoryCreateRequest.productId())
                .availableQuantity(inventoryCreateRequest.quantity())
                .reservedQuantity(0)
                .status("ACTIVE")
                .build();
        Inventory saved = inventoryRepository.save(inventory);
        return toResponse(saved);
    }

    @Override
    public InventoryResponse getInventory(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(
                        () -> new InventoryNotFoundException(
                                "Inventory not found for product: "
                                        + productId
                        )
                );
        return toResponse(inventory);
    }

    @Override
    public StockResponse reserveStock(StockReserveRequest stockReserveRequest) {

        return null;
    }

    private InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getAvailableQuantity(),
                inventory.getReservedQuantity(),
                inventory.getStatus(),
                inventory.getUpdatedAt()
        );
    }
}
