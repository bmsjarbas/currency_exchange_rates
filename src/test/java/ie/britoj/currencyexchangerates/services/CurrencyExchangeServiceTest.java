package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.configurations.WebMvcConfiguration;
import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@Transactional
@TestPropertySource("classpath:application-test.properties")
public class CurrencyExchangeServiceTest {

    @Autowired
    ExchangeRatesService currencyExchangeService;

    @Test
    public void retrieveActualCurrencyExchangeReturnAPositiveValue(){
        ExchangeRatesQueryResult currencyExchangeRate = this.currencyExchangeService.retrieveActualCurrencyExchangeRate("BRL");
        assertThat(currencyExchangeRate).isNotNull();
        assertThat(currencyExchangeRate.getRates()).containsKey("USD");

    }

    @Test
    public void retrieveAllAvailableCurrencies(){
        Map<String, String> currencies = this.currencyExchangeService.retrieveAllCurrencies();
        assertThat(currencies).containsKey("USD");
    }

}
