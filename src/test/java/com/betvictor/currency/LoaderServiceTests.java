package com.betvictor.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.betvictor.currency.entity.ExchangeRates;
import com.betvictor.currency.service.LoaderService;
import com.betvictor.currency.service.LoaderService.CurrencySource;

@SpringBootTest
class LoaderServiceTests {

	@Autowired
    private LoaderService loaderService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(loaderService).isNotNull();
	}

	@Test
	void testExchangeRate() {
		ExchangeRates rates = loaderService.loadRates(CurrencySource.EXCHANGERATE, "EUR");

		assertThat(rates.getBase()).isEqualTo("EUR");
		assertThat(rates.getRates().size()).isGreaterThan(100);
	}

	@Test
	void testOpenExchangeRates() {
		ExchangeRates rates = loaderService.loadRates(CurrencySource.OPENEXCHANGERATES, "USD");

		assertThat(rates.getBase()).isEqualTo("USD");
		assertThat(rates.getRates().size()).isGreaterThan(100);
	}
}
