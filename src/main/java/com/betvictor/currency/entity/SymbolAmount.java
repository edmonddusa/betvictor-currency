package com.betvictor.currency.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SymbolAmount {

    private String symbol;
    private BigDecimal amount;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SymbolAmount(String symbol, BigDecimal amount) {
        this.symbol = symbol;
        this.amount = amount;
    }
}
