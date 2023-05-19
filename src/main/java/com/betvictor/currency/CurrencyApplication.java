package com.betvictor.currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CurrencyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
