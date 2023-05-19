package com.betvictor.currency.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.betvictor.currency.entity.CurrencyExchange;
import com.betvictor.currency.entity.ExchangeRates;

@Service
@CacheConfig(cacheNames = { "symbols" })
public class CurrencyConversionService {

    @Autowired
    private LoaderService loaderService;

    @Cacheable(key = "#baseSymbol")
    public ExchangeRates getExchangeRates(String baseSymbol) {
        return loaderService.loadRates(baseSymbol);
    }

    public CurrencyExchange convert(String fromSymbol, String toSymbol, Double amount) {
        ExchangeRates rates = getExchangeRates(fromSymbol);

        if (rates.getRates().containsKey(toSymbol)) {
            return new CurrencyExchange(fromSymbol, amount, toSymbol, rates.getRates().get(toSymbol) * amount);
        } else
            throw new IllegalArgumentException("No such symbol " + toSymbol);
    }

    public List<CurrencyExchange> convert(String fromSymbol, List<String> toSymbols, Double amount) {
        ArrayList<CurrencyExchange> list = new ArrayList<CurrencyExchange>();

        toSymbols.stream().forEach(s -> list.add(convert(fromSymbol, s, amount)));

        return list;
    }
}
