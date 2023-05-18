package com.betvictor.currency.entities;

import java.util.Date;
import java.util.HashMap;

public class ExchangeRates {
    public String base;
    public String source;
    public Date timestamp;    
    public HashMap<String, Double> rates;

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

    public ExchangeRates(com.betvictor.currency.entities.exchangerate.ExchangeRate rates) {
        this.base = rates.getBase();
        this.source = "exchangerate";
        this.timestamp = new Date();
        this.rates = rates.getRates();
    }
}
