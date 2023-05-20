package com.betvictor.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.betvictor.currency.entity.CurrencyExchange;
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
	void testCache() throws ExecutionException {
		ExchangeRates rates0 = conversionService.getExchangeRates("EUR");
		ExchangeRates rates1 = conversionService.getExchangeRates("EUR");

		assertThat(rates0.getTimestamp()).isEqualTo(rates1.getTimestamp());
		assertThat(rates0.getSource()).isEqualTo(rates1.getSource());
	}

	@Test
	void testEURtoEUR() throws ExecutionException {
		CurrencyExchange convert = conversionService.convert("EUR", "EUR", new BigDecimal("1000.00"));

		assertThat(convert.getFrom().getSymbol()).isEqualTo("EUR");
		assertThat(convert.getTo().getSymbol()).isEqualTo("EUR");
		assertThat(convert.getTo().getAmount().compareTo(convert.getFrom().getAmount())).isEqualTo(0);
	}

	@Test
	void testEURtoUSD() throws ExecutionException {
		CurrencyExchange convert = conversionService.convert("EUR", "USD", new BigDecimal("1000.00"));

		assertThat(convert.getFrom().getSymbol()).isEqualTo("EUR");
		assertThat(convert.getTo().getSymbol()).isEqualTo("USD");
	}

	@Test
	void testBadSymbol() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			conversionService.convert("EUR", Arrays.asList("USD", "XXX", "NOK"), new BigDecimal("1000.00"));
		});

		String expectedMessage = "No such symbol XXX";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testList() {
		List<CurrencyExchange> converts = conversionService.convert("EUR", Arrays.asList("USD", "SEK", "NOK"), new BigDecimal("1000.00"));

		assertThat(converts.size()).isEqualTo(3);
		assertThat(converts.get(0).getFrom().getSymbol()).isEqualTo("EUR");
		assertThat(converts.get(1).getFrom().getSymbol()).isEqualTo("EUR");
		assertThat(converts.get(2).getFrom().getSymbol()).isEqualTo("EUR");
		assertThat(converts.get(0).getTo().getSymbol()).isEqualTo("USD");
		assertThat(converts.get(1).getTo().getSymbol()).isEqualTo("SEK");
		assertThat(converts.get(2).getTo().getSymbol()).isEqualTo("NOK");
	}

	@Test
	void testRebase() throws ExecutionException {
		double epsilon = 0.1d;

		ExchangeRates eurRates = conversionService.getExchangeRates("EUR");
		ExchangeRates usdRates = conversionService.getExchangeRates("USD");
		ExchangeRates rebaseRate = eurRates.rebase("USD");

		assertThat(eurRates.getBase()).isEqualTo("EUR");
		assertThat(usdRates.getRates().size()).isEqualTo(rebaseRate.getRates().size());

		HashMap<String, Double> rates = rebaseRate.getRates();

		usdRates.getRates().forEach((key, value) -> {
			assertThat(Math.abs(value - rates.get(key)) < epsilon).isTrue();
		});
	}
}
