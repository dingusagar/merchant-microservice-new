package com.example.merchantmicroservice.repository;

import com.example.merchantmicroservice.entity.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MerchantRepository extends MongoRepository<Merchant, String> {

}
