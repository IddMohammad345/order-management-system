package com.idd.inventoryservice.service;

import com.idd.inventoryservice.dto.InventoryCreateRequest;
import com.idd.inventoryservice.dto.InventoryResponse;
import com.idd.inventoryservice.dto.StockReserveRequest;
import com.idd.inventoryservice.dto.StockResponse;

public interface InventoryService {
    InventoryResponse createInventory(
            InventoryCreateRequest inventoryCreateRequest
    );

    InventoryResponse getInventory(Long productId);

    StockResponse reserveStock(StockReserveRequest stockReserveRequest);
}
