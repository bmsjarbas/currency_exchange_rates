package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;

import java.util.Map;

public interface CurrencyExchangeProvider{
    ExchangeRatesQueryResult retrieveActualCurrencyExchangeRate(String currencyBase);
    Map<String, String> retrieveAllCurrencies();
}
