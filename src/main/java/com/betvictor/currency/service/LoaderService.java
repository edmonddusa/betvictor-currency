package com.betvictor.currency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.betvictor.currency.entity.ExchangeRates;

@Service
public class LoaderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${exchangerate.url}")
    private String exchangeRateUrl;

    @Value("${exchangerate.base}")
    private String exchangeRateBase;

    @Value("${openexchangerates.url}")
    private String openExchangeRateUrl;

    @Value("${openexchangerates.key}")
    private String openExchangeRateKey;

    public enum CurrencySource {
        EXCHANGERATE,
        OPENEXCHANGERATES,
    }

    public ExchangeRates loadRates(String baseSymbol) {
        log.info(String.format("Loading currency rates for %s ...", baseSymbol));
        
        try {
            return loadRates(CurrencySource.EXCHANGERATE, baseSymbol);
        } catch (Exception e) {
            return loadRates(CurrencySource.OPENEXCHANGERATES, baseSymbol);
        }
    }

    public ExchangeRates loadRates(CurrencySource source, String baseSymbol) {
        String uri = getUri(source, baseSymbol);

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRates exchangeRates = null;

        switch (source) {
            default:
            case EXCHANGERATE:
                exchangeRates = new ExchangeRates(restTemplate.getForObject(uri,
                        com.betvictor.currency.entity.exchangerate.ExchangeRate.class));

                break;

            case OPENEXCHANGERATES:
                exchangeRates = new ExchangeRates(restTemplate.getForObject(uri,
                        com.betvictor.currency.entity.openexchangerates.OpenExchangeRates.class));
                if (!baseSymbol.equals(exchangeRates.getBase())) {
                    exchangeRates = exchangeRates.rebase(baseSymbol);
                }

                break;
        }

        return exchangeRates;
    }

    private String getUri(CurrencySource source, String baseSymbol) {
        switch (source) {
            default:
            case EXCHANGERATE:
                return String.format("%s?base=%s", exchangeRateUrl, baseSymbol);
            case OPENEXCHANGERATES:
                return String.format("%s?app_id=%s&prettyprint=true&show_alternative=false", openExchangeRateUrl,
                        openExchangeRateKey);
        }
    }
}
