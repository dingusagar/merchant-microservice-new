package com.example.merchantmicroservice.repository;

import com.example.merchantmicroservice.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByProductIdAndVariantId(String productId, String variantId);

    Inventory findByProductIdAndVariantIdAndMerchantId(String productId, String variantId, String merchantId);
}
