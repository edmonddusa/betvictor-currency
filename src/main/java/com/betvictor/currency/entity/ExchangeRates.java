package com.betvictor.currency.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExchangeRates {

    private String base;
    private String source;
    private Date timestamp;
    private HashMap<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    @JsonIgnore
    public ExchangeRates rebase(String base) {
        if (this.rates.containsKey(base)) {
            Double rate = this.rates.get(base);
            Double multiplier = 1 / rate;

            HashMap<String, Double> map = new HashMap<String, Double>();
            Set<Entry<String, Double>> entries = this.rates.entrySet();
            for (Map.Entry<String, Double> e : entries) {
                map.put(e.getKey(), multiplier * e.getValue());
            }

            return new ExchangeRates(base, this.source, map);
        } else
            throw new IllegalArgumentException("Invalid base: " + base);
    }

    public ExchangeRates(String base, String source, HashMap<String, Double> rates) {
        this.base = base;
        this.source = source;
        this.timestamp = new Date();
        this.rates = rates;
    }

    public ExchangeRates(com.betvictor.currency.entity.exchangerate.ExchangeRate rates) {
        this(rates.getBase(), "exchangerate", rates.getRates());

    }

    public ExchangeRates(com.betvictor.currency.entity.openexchangerates.OpenExchangeRates rates) {
        this(rates.getBase(), "openexchangerates", rates.getRates());
    }
}
