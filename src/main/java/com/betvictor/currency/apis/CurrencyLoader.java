package com.betvictor.currency.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.betvictor.currency.entities.ExchangeRates;


@Service
public class CurrencyLoader {

    @Value( "${exchangerate.url}" )
    private String exchangeRateUrl;

    @Value( "${exchangerate.base}" )
    private String exchangeRateBase;

    @Value( "${openexchangerates.url}" )
    private String openExchangeRateUrl;

    @Value( "${openexchangerates.key}" )
    private String openExchangeRateKey;

    public enum CurrencySource {
        EXCHANGERATE,
        OPENEXCHANGERATES,
    }

    public ExchangeRates loadRates(CurrencySource source) {
        String uri = getUri(source);

        RestTemplate restTemplate = new RestTemplate();
        
        switch (source) {
            default:
            case EXCHANGERATE:
                return new ExchangeRates(restTemplate.getForObject(uri, com.betvictor.currency.entities.exchangerate.ExchangeRate.class));
            
            case OPENEXCHANGERATES:
                return new ExchangeRates(restTemplate.getForObject(uri, com.betvictor.currency.entities.exchangerate.ExchangeRate.class));
        }
    }

    private String getUri(CurrencySource source) {
        switch (source) {
            default:
            case EXCHANGERATE:
                return String.format("%s?base=%s", exchangeRateUrl, exchangeRateBase);
            case OPENEXCHANGERATES:
                return String.format("%s?app_id=%s&prettyprint=true&show_alternative=false", openExchangeRateUrl, exchangeRateBase);
        }
    }
}
