package com.idd.inventoryservice.repository;

import com.idd.inventoryservice.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction,Long> {
}
