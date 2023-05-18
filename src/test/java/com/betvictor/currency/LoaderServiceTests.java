package com.betvictor.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.betvictor.currency.entities.ExchangeRates;
import com.betvictor.currency.service.LoaderService;
import com.betvictor.currency.service.LoaderService.CurrencySource;

@SpringBootTest
class LoaderServiceTests {

	@Autowired
    private LoaderService loaderService;

	@Test
	void testExchangeRate() {
		ExchangeRates rates = loaderService.loadRates(CurrencySource.EXCHANGERATE, "EUR");

		assertThat(rates.base).isEqualTo("EUR");
		assertThat(rates.rates.size()).isGreaterThan(100);
	}

	@Test
	void testOpenExchangeRates() {
		ExchangeRates rates = loaderService.loadRates(CurrencySource.OPENEXCHANGERATES, "USD");

		assertThat(rates.base).isEqualTo("USD");
		assertThat(rates.rates.size()).isGreaterThan(100);
	}

}
