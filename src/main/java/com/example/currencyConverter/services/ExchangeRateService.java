package com.example.currencyConverter.services;

import java.util.Map;

import com.example.currencyConverter.entities.Exchange;

public interface ExchangeRateService {

	Double exchange(Exchange exchange);

	Map<String, Double> showRatesTable();

	Exchange save(Exchange exchange);
}
