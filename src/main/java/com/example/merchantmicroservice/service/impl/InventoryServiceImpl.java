package com.example.merchantmicroservice.service.impl;

import com.example.merchantmicroservice.entity.Inventory;
import com.example.merchantmicroservice.model.InventoryDTO;
import com.example.merchantmicroservice.model.MerchantDTO;
import com.example.merchantmicroservice.repository.InventoryRepository;
import com.example.merchantmicroservice.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MerchantServiceImpl merchantService;

    @Override
    public void addInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryDTO,inventory);
        final String url = "http://localhost:8080/getDetailsForCart?productId="+inventory.getProductId();

        RestTemplate restTemplate = new RestTemplate();

        List<String> details = restTemplate.getForObject(url, List.class);
        inventory.setProductName(details.get(0));
        inventory.setProductImage(details.get(2));
        inventory.setProductCategory(details.get(1));

        MerchantDTO merchantDTO = merchantService.getMerchant(inventoryDTO.getMerchantId());
        inventory.setMerchantName(merchantDTO.getMerchantName());
        inventoryRepository.save(inventory);
    }

    @Override
    public void removeInventory(String productId, String variantId, String merchantId) {
        Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
            inventoryRepository.delete(inventory);
    }

    @Transactional
    @Override
    public synchronized  void increaseInventoryStockAvailable(String productId, String variantId, String merchantId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
        if(quantity > 0){
            inventory.setStockAvailable(inventory.getStockAvailable()+quantity);
        }
    }


    @Transactional
    @Override
    public synchronized void decreaseInventoryStockAvailableAndUpdateStockSold(String productId, String variantId, String merchantId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
        Integer stockAvailable = inventory.getStockAvailable();
        if(quantity > 0 && (stockAvailable - quantity) >= 0){
            inventory.setStockAvailable(stockAvailable-quantity);
            inventory.setStockSold(inventory.getStockSold() + quantity);
        }
    }

    @Override
    public List<Inventory> getMerchantsByProduct(String productId, String variantId) {
        List<Inventory> list = inventoryRepository.findByProductIdAndVariantId(productId, variantId);
        merchantOptimizer(list);
        list.sort(Comparator.comparing(Inventory::getAggregatedScore).reversed());
        return list;
    }

    public void merchantOptimizer(List<Inventory> list) {
        Double totalUserRating = 0.0;
        Double totalPrice = 0.0;
        Integer totalStockSold = 0;

        for(Inventory inventory : list){

            Double price = inventory.getPrice();
            Double userRating = inventory.getAverageUserRating();
            Integer stockSold = inventory.getStockSold();
            if(price != null){
                totalPrice += price;
            }

            if(userRating != null){
                totalUserRating += userRating;
            }

            if(stockSold != null){
                totalStockSold += stockSold;
            }
        }
        Double weight1 = 1.0 , weight2 = 1.0 , weight3 = 1.0;

        for(Inventory inventory : list) {

            Double aggregatedScore = 0.0;
            Double price = inventory.getPrice();
            Double userRating = inventory.getAverageUserRating();
            Integer stockSold = inventory.getStockSold();

            if(price != null && totalPrice != null){
                aggregatedScore += weight1 * totalPrice / price;
            }

            if(userRating != null && totalUserRating != null){
                aggregatedScore += weight2 * userRating / totalUserRating;
            }

            if(stockSold != null && totalStockSold != null){
                aggregatedScore += weight3 * stockSold / totalStockSold;
            }

            inventory.setAggregatedScore(aggregatedScore);

        }

    }

    @Override
    public void setPrice(String productId, String variantId, String merchantId, double price) {
     Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
        System.out.println(inventory);
     inventory.setPrice(price);
        System.out.println("updated");
    }

    @Override
    public int getStock(String productId,String variantId, String merchantId) {
        Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
        System.out.println(inventory);
        return inventory.getStockAvailable();
    }
    @Override
    public boolean checkStock(String productId,String variantId, String merchantId) {
        Inventory inventory = inventoryRepository.findByProductIdAndVariantIdAndMerchantId(productId, variantId, merchantId);
        if(inventory.getStockAvailable()==0) {
            return false;
        } else {
            return true;
        }
    }
}
