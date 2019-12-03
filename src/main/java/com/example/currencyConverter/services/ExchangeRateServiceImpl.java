package com.example.currencyConverter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.currencyConverter.dto.Rate;
import com.example.currencyConverter.dto.RatesTable;
import com.example.currencyConverter.entities.Exchange;
import com.example.currencyConverter.repositories.ExchangeRepository;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	@Autowired
	private RatesTable ratesTable;

	@Autowired
	private ExchangeRepository exchangeRepository;

	private String NbpApiUrl = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";

	@Override
	public Map<String, Double> showRatesTable() {
		loadTable();

		List<Rate> rate = ratesTable.getRates();
		Map<String, Double> o = new HashMap<>();
		for (Rate rates : rate)
			o.put(rates.getCode(), rates.getMid());
		Map<String, Double> sorted = o.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		return sorted;

	}

	@Override
	public Double exchange(Exchange exchange) {
		loadTable();
		Double baseCurrencyRate = getMid(exchange.getBaseCurrency());
		Double targetCurrencyRate = getMid(exchange.getTargetCurrency());
		Double value = exchange.getValue();

		Double baseValue = value * baseCurrencyRate;
		Double finalValue = baseValue / targetCurrencyRate;
		exchange.setBaseMid(baseCurrencyRate);
		exchange.setTargetMid(targetCurrencyRate);
		exchange.setFinalValue(finalValue);
		save(exchange);
		return finalValue;
	}

	@Override
	public Exchange save(Exchange exchange) {
		return exchangeRepository.save(exchange);

	}

	private void loadTable() {
		RestTemplate restTemplate = new RestTemplate();
		List<Rate> rate = new ArrayList<>();
		try {
			ResponseEntity<List<RatesTable>> rateResponse = restTemplate.exchange(NbpApiUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<RatesTable>>() {
					});

			List<RatesTable> ratesTableList = rateResponse.getBody();
			if (!CollectionUtils.isEmpty(ratesTableList)) {
				rate.addAll(ratesTableList.get(0).getRates());
				rate.add(plnRate());
				ratesTable.setRates(rate);

			}
		} catch (RestClientException ex) {
			throw new IllegalStateException("NBP convert rates api unavailable");
		}
	}

	private Double getMid(String currency) {

		Rate rate = ratesTable.getRates().stream().filter(r -> r.getCode().contentEquals(currency)).findFirst()
				.orElse(null);
		if (rate == null) {
			throw new IllegalStateException(String.format("Unknown currency code %s", currency));
		}
		return rate.getMid();
	}

	private Rate plnRate() {
		Rate rate = new Rate();
		rate.setCode("PLN");
		rate.setCurrency("polski złoty nowy");
		rate.setMid(1.0);
		return rate;
	}
}
