package com.betvictor.currency.entities;

import java.util.HashMap;

public class CacheEntry {
    public String source;
    public String base;
    public HashMap<String, Double> rates;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}
