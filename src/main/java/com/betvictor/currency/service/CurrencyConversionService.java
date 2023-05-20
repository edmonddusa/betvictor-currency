package com.betvictor.currency.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betvictor.currency.entity.CurrencyExchange;
import com.betvictor.currency.entity.ExchangeRates;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CurrencyConversionService {

    @Autowired
    private LoaderService loaderService;

    LoadingCache<String, ExchangeRates> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, ExchangeRates>() {

                        @Override
                        public ExchangeRates load(String key) {
                            return loaderService.loadRates(key);
                        }
                    });

    public ExchangeRates getExchangeRates(String baseSymbol) throws ExecutionException {
        return cache.get(baseSymbol);
    }

    public CurrencyExchange convert(String fromSymbol, String toSymbol, BigDecimal amount) throws ExecutionException {
        ExchangeRates rates = getExchangeRates(fromSymbol);

        if (rates.getRates().containsKey(toSymbol)) {
            return new CurrencyExchange(fromSymbol, amount, toSymbol, amount.multiply(BigDecimal.valueOf(rates.getRates().get(toSymbol))));
        } else
            throw new IllegalArgumentException("No such symbol " + toSymbol);
    }

    public List<CurrencyExchange> convert(String fromSymbol, List<String> toSymbols, BigDecimal amount) {
        ArrayList<CurrencyExchange> list = new ArrayList<CurrencyExchange>();

        toSymbols.stream().forEach(s -> {
            try {
                list.add(convert(fromSymbol, s, amount));
            } catch (ExecutionException e) {
            }
        });

        return list;
    }
}
