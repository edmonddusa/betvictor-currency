package com.betvictor.currency.entity.openexchangerates;

import java.util.HashMap;

public class OpenExchangeRates {

    public String disclaimer;
    public String license;
    public int timestamp;
    public String base;
    public HashMap<String, Double> rates;

    public String getDisclaimer() {
        return this.disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public HashMap<String, Double> getRates() {
        return this.rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}
