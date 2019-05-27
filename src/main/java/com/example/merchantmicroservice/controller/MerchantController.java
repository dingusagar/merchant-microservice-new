package com.example.merchantmicroservice.controller;

import com.example.merchantmicroservice.entity.Inventory;
import com.example.merchantmicroservice.model.InventoryDTO;
import com.example.merchantmicroservice.model.MerchantDTO;
import com.example.merchantmicroservice.service.impl.InventoryServiceImpl;
import com.example.merchantmicroservice.service.impl.MerchantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchantController {

    @Autowired
    private MerchantServiceImpl merchantServiceimpl;

    @Autowired
    private InventoryServiceImpl inventoryServiceimpl;

    @RequestMapping(method = RequestMethod.GET, value = "/getMerchant")
    MerchantDTO getMerchant(@RequestParam String merchantId) {
        return merchantServiceimpl.getMerchant(merchantId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMerchant")
    void addMerchant(@RequestBody MerchantDTO merchantDTO) {
        merchantServiceimpl.addMerchant(merchantDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteMerchant")
    MerchantDTO deleteMerchant(@RequestParam String merchantId) {
        return merchantServiceimpl.deleteMerchant(merchantId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addInventory")
    void addInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryServiceimpl.addInventory(inventoryDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/removeInventory")
    void removeInventory(@RequestParam String productId, @RequestParam String variantId, @RequestParam String merchantId) {
        inventoryServiceimpl.removeInventory(productId, variantId, merchantId);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/getMerchants")
    List<Inventory> getMerchants(@RequestParam String productId, @RequestParam String variantId) {
        return inventoryServiceimpl.getMerchantsByProduct(productId, variantId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setPrice")
    void setPrice(@RequestParam String productId, @RequestParam String variantId, @RequestParam String merchantId, @RequestParam double price) {
        inventoryServiceimpl.setPrice(productId, variantId, merchantId, price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStock")
    int getStock(@RequestParam String productId, @RequestParam String variantId, @RequestParam String merchantId) {
        return inventoryServiceimpl.getStock(productId, variantId, merchantId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkStock")
    boolean checkStock(@RequestParam String productId, @RequestParam String variantId, @RequestParam String merchantId) {
        return inventoryServiceimpl.checkStock(productId, variantId, merchantId);
    }
}
