# CurrencyConverter

Aplikacja służąca do przeglądania oraz wymiany walut. Aktualne kursy walut pobierane są z Api udostępnionego przez Narodowy Bank Polski. 

## Dostępne serwisy REST
### Metoda GET
http://localhost:9200/api/showCurrency

### Metoda POST
http://localhost:9200/api/convert  
Parametry:  
  - value- kwota do przeliczenia  
  - baseCurrency- kod waluty z której będziemy przeliczać  
  - targetCurrency- kod waluty na która będziemy przeliczać    
  
Przykładowe zapytanie:
```
{
		"value" : "1",
		"baseCurrency" : "EUR",
		"targetCurrency" : "PLN"
}
```

