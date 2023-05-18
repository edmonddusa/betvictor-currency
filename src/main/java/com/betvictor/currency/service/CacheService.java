package com.betvictor.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.betvictor.currency.entities.ExchangeRates;

@Service
public class CacheService {

    @Value( "${currency.cache.enabled}" )
    private Boolean cachingEnabled;

    @Autowired
    public CacheManager cacheManager;

    @Autowired
    private LoaderService loaderService;

    public ExchangeRates getRates(String baseSymbol) { 
        if(cachingEnabled) {
            


        } else {
            loaderService.loadRates(baseSymbol);
        }

        return null;
    }

   
    
}
