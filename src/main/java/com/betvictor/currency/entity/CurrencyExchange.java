package com.betvictor.currency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyExchange {

    @JsonProperty("from") 
    private SymbolAmount from;
    
    @JsonProperty("to") 
    private SymbolAmount to;

    public SymbolAmount getFrom() {
        return this.from;
    }

    public void setFrom(SymbolAmount from) {
        this.from = from;
    }

    public SymbolAmount getTo() {
        return this.to;
    }

    public void setTo(SymbolAmount to) {
        this.to = to;
    }

    public CurrencyExchange(String fromSymbol, Double fromAmount, String toSymbol, Double toAmount) {
        this.from = new SymbolAmount(fromSymbol, fromAmount);
        this.to = new SymbolAmount(toSymbol, toAmount);
    }
}

