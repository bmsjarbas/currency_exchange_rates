package ie.britoj.currencyexchangerates.web.viewmodels;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class HistoryItemViewModel {
    private String baseCurrency;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public HistoryItemViewModel(String baseCurrency, LocalDate date) {
        this.baseCurrency = baseCurrency;
        this.date = date;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }


    public LocalDate getDate() {
        return date;
    }
}
