package com.example.merchantmicroservice.service;

import com.example.merchantmicroservice.entity.Inventory;
import com.example.merchantmicroservice.model.InventoryDTO;

import java.util.List;

public interface InventoryService {
    void addInventory(InventoryDTO inventoryDTO);
    void removeInventory(String productId,String variantId, String merchantId);
    void increaseInventoryStockAvailable(String productId, String variantId, String merchantId, int quantity);
    void decreaseInventoryStockAvailableAndUpdateStockSold(String productId, String variantId, String merchantId, int quantity);
    List<Inventory> getMerchantsByProduct(String productId, String variantId);
    void setPrice(String productId,String variantId, String merchantId, double price);
    int getStock(String productId,String variantId, String merchantId);
    boolean checkStock(String productId,String variantId, String merchantId);
}
