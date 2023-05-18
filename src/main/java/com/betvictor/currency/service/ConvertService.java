package com.betvictor.currency.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ConvertService {

    public Double convert(String fromSymbol, String toSymbol, Double amount) {
        return 0.0;
    }

    public Double convert(String fromSymbol, List<String> toSymbols, Double amount) {
        return 0.0;
    }
}
