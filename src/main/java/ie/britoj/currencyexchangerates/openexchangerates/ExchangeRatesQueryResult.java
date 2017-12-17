package ie.britoj.currencyexchangerates.openexchangerates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesQueryResult
{

    private boolean hasError;
    private String error;
    private String baseCurrency;
    private Map<String, Double> rates;


    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
