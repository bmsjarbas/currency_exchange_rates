package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.services.CurrencyExchangeProvider;
import ie.britoj.currencyexchangerates.web.viewmodels.SearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CurrencyExchangeProvider currencyExchangeProvider;
    @GetMapping
    public ModelAndView index(){
        Map<String, String> allCurrencies = currencyExchangeProvider.retrieveAllCurrencies();
        SearchViewModel searchViewModel = new SearchViewModel(allCurrencies);
        ModelAndView modelAndView = new ModelAndView();
        return new ModelAndView("index", "search", searchViewModel);
    }
}
