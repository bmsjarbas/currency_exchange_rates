package ie.britoj.currencyexchangerates.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class ExchangeRatesService implements CurrencyExchangeProvider {


    @Autowired
    RestOperations restOperations;

    @Autowired
    Environment environment;

    @Override
    public Map<String, String> retrieveAllCurrencies() {
        String currencyResource = environment.getRequiredProperty("currency-list-api.endpoint");
        return restOperations.getForObject(currencyResource, Map.class);

    }

    @Override
    public ExchangeRatesQueryResult retrieveHistoricalCurrencyExchangeRate(String currencyBase, LocalDate localDate) {

        ExchangeRatesQueryResult result = new ExchangeRatesQueryResult();
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String latestExchangesURI = getExchangeRatesURI(formattedDate) + "?base=" + currencyBase;
        try {
            result = restOperations.getForObject(latestExchangesURI, ExchangeRatesQueryResult.class);
        } catch (RestClientException exception) {
            if (exception instanceof HttpStatusCodeException) {
                String rawJsonResponse = ((HttpStatusCodeException) exception).getResponseBodyAsString();
                Map<String, String> errorResponse = null;
                try {
                    errorResponse = new ObjectMapper().readValue(rawJsonResponse, Map.class);
                    /*Surrounding because it ~always~ be a string and ~never~ a file*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.setHasError(true);
                result.setError(errorResponse.get("error"));
            }
        }
        return result;

    }

    private String getExchangeRatesURI(String resource) {
        String apiEndpoint = this.environment.getRequiredProperty("currency-exchange-api.endpoint");
        return apiEndpoint + resource;
    }

    /* This method is not being used at the moment. It has been implemented in order to have all external api supported methods */
    public ExchangeRatesQueryResult retrieveActualCurrencyExchangeRate(String currencyBase) {
        String latestExchangesURI = getExchangeRatesURI("latest") + "?base=" + currencyBase;
        return restOperations.getForObject(latestExchangesURI, ExchangeRatesQueryResult.class);
    }
}
