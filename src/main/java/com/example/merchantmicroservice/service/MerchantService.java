package com.example.merchantmicroservice.service;

import com.example.merchantmicroservice.entity.Merchant;
import com.example.merchantmicroservice.model.MerchantDTO;

import java.util.List;

public interface MerchantService {
    void addMerchant(MerchantDTO merchantDTO);
    MerchantDTO getMerchant(String merchantId);
    MerchantDTO deleteMerchant(String merchantId);
}
