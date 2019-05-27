package com.example.merchantmicroservice.service.impl;

import com.example.merchantmicroservice.entity.Inventory;
import com.example.merchantmicroservice.entity.Merchant;
import com.example.merchantmicroservice.model.MerchantDTO;
import com.example.merchantmicroservice.repository.MerchantRepository;
import com.example.merchantmicroservice.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;
    @Override
    public void addMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        merchantRepository.save(merchant);
    }

    @Override
    public MerchantDTO getMerchant(String merchantId) {
        Merchant merchant = merchantRepository.findOne(merchantId);
        MerchantDTO merchantDTO = new MerchantDTO();
        BeanUtils.copyProperties(merchant,merchantDTO);
        return merchantDTO;
    }


    @Override
    public MerchantDTO deleteMerchant(String merchantId) {
        Merchant merchant = merchantRepository.findOne(merchantId);
        MerchantDTO merchantDTO = new MerchantDTO();
        BeanUtils.copyProperties(merchant,merchantDTO);
        merchantRepository.delete(merchantId);
        return merchantDTO;
    }


}
