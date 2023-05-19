package com.betvictor.currency.entity;

public class SymbolAmount {

    private String symbol;
    private Double amount;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SymbolAmount(String symbol, Double amount) {
        this.symbol = symbol;
        this.amount = amount;
    }
}
