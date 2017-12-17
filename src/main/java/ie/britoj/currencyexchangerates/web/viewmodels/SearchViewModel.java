package ie.britoj.currencyexchangerates.web.viewmodels;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;

public class SearchViewModel {

    private String currency;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private  Map<String, Double> exchangeRates;

    public SearchViewModel(){}


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
