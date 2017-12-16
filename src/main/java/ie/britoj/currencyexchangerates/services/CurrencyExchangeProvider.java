package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;

import java.time.LocalDate;
import java.util.Map;

public interface CurrencyExchangeProvider{
    ExchangeRatesQueryResult retrieveActualCurrencyExchangeRate(String currencyBase);
    Map<String, String> retrieveAllCurrencies();
    ExchangeRatesQueryResult retrieveHistoricalCurrencyExchangeRate(String currencyBase, LocalDate localDate);
}
