package com.betvictor.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.betvictor.currency.entity.ExchangeRates;
import com.betvictor.currency.service.CurrencyConversionService;


@SpringBootTest
class CurrencyConversionServiceTests {

	@Autowired
    private CurrencyConversionService conversionService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(conversionService).isNotNull();
	}

	@Test
	void testCache() {
		ExchangeRates rates0 = conversionService.getExchangeRates("EUR");
		ExchangeRates rates1 = conversionService.getExchangeRates("EUR");

		assertThat(rates0.getTimestamp()).isEqualTo(rates1.getTimestamp());
		assertThat(rates0.getSource()).isEqualTo(rates1.getSource());
	}

	
}
