package com.betvictor.currency.entity;

import java.math.BigDecimal;

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

    public CurrencyExchange(String fromSymbol, BigDecimal fromAmount, String toSymbol, BigDecimal toAmount) {
        this.from = new SymbolAmount(fromSymbol, fromAmount);
        this.to = new SymbolAmount(toSymbol, toAmount);
    }
}

