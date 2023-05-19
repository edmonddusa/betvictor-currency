package com.betvictor.currency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.betvictor.currency.entity.ExchangeRates;

@Service
@CacheConfig(cacheNames = {"currency"})
public class CurrencyConversionService {

    @Autowired
    private LoaderService loaderService;

    @Cacheable
    public ExchangeRates getExchangeRates(String baseSymbol) {
        return loaderService.loadRates(baseSymbol);
    }

    public Double convert(String fromSymbol, String toSymbol, Double amount) {
        return 0.0;
    }

    public Double convert(String fromSymbol, List<String> toSymbols, Double amount) {
        return 0.0;
    }
}
