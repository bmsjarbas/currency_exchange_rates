package ie.britoj.currencyexchangerates.web.viewmodels;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;

public class SearchViewModel {

    private Map<String, String> allCurrencies;
    private String currency;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public SearchViewModel(){}

    public SearchViewModel(Map<String, String> currencies){
        this.allCurrencies = currencies;
    }
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

    public Map<String, String> getAllCurrencies() {
        return allCurrencies;
    }

    public void setAllCurrencies(Map<String, String> allCurrencies) {
        this.allCurrencies = allCurrencies;
    }
}
