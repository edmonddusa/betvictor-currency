package com.betvictor.currency.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.betvictor.currency.entity.CurrencyExchange;
import com.betvictor.currency.entity.ExchangeRates;
import com.betvictor.currency.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService conversionService;

    @RequestMapping(value = "/rates/{baseSymbol}", method = RequestMethod.GET)
    public ExchangeRates getRates(@PathVariable("baseSymbol") String baseSymbol) throws ExecutionException {
        return conversionService.getExchangeRates(baseSymbol.toUpperCase());
    }

    @RequestMapping(value = "/{fromSymbol}/{toSymbols}/{amount}", method = RequestMethod.GET)
    public List<CurrencyExchange> convert(@PathVariable("fromSymbol") String fromSymbol,
            @PathVariable("toSymbols") String toSymbols, @PathVariable("amount") String amount) {

        return conversionService.convert(fromSymbol.toUpperCase(), Arrays.asList(toSymbols.split("\\s*,\\s*")).stream().map(String::toUpperCase).collect(Collectors.toList()), new BigDecimal(amount));
    }
}