package com.example.currencyConverter.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyConverter.entities.Exchange;
import com.example.currencyConverter.services.ExchangeRateService;

@RestController
@RequestMapping("/api")
public class ExchangeController {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@PostMapping("/convert")
	public Double exchange(@RequestBody Exchange exchange) {
		return exchangeRateService.exchange(exchange);

	}

	@GetMapping("/showCurrency")
	public Map<String, Double> ratesTable() {
		return exchangeRateService.showRatesTable();

	}

}
