package com.betvictor.currency;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 
import com.betvictor.currency.entities.ExchangeRates;
import com.betvictor.currency.service.LoaderService;
import com.betvictor.currency.service.LoaderService.CurrencySource;
*/

@SpringBootApplication
public class CurrencyApplication implements CommandLineRunner {

	//@Autowired
    //private LoaderService loaderService;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ExchangeRates rates = loaderService.loadRates(CurrencySource.OPENEXCHANGERATES, "EUR");
	}

}
