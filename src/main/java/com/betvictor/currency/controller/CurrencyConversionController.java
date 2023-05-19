package com.betvictor.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.betvictor.currency.entity.ExchangeRates;
import com.betvictor.currency.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService conversionService;

    @RequestMapping(value = "/rates/{baseSymbol}", method = RequestMethod.GET)
    public ExchangeRates getRates(@PathVariable("baseSymbol") String baseSymbol) {
        return conversionService.getExchangeRates(baseSymbol);
    }
}