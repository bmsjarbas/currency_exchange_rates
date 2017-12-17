package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;
import ie.britoj.currencyexchangerates.services.CurrencyExchangeProvider;
import ie.britoj.currencyexchangerates.web.validators.SearchValidator;
import ie.britoj.currencyexchangerates.web.viewmodels.SearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
@RequestMapping("/")
@Transactional
public class HomeController {
    @Autowired
    CurrencyExchangeProvider currencyExchangeProvider;
    @Autowired
    SearchValidator searchValidator;

    @GetMapping
    public ModelAndView index(SearchViewModel searchViewModel){
        ModelAndView modelAndView = new ModelAndView("index", "search", searchViewModel);
        return modelAndView;
    }


    @PostMapping
    public String search(
        @ModelAttribute("search") SearchViewModel searchViewModel,
        BindingResult bindingResult, RedirectAttributes redirectAttributes){
        searchValidator.validate(searchViewModel, bindingResult);

        if(bindingResult.hasErrors()){
            return "index";
        }

        ExchangeRatesQueryResult result = currencyExchangeProvider
                .retrieveHistoricalCurrencyExchangeRate(searchViewModel.getCurrency(), searchViewModel.getDate());

        searchViewModel.setExchangeRates(result.getRates());

        if(result.hasError()){
            bindingResult.rejectValue("currency",
                    "searchForm.Api."+ result.getError().replace(" " ,""));

        }

        return "index";


    }
    @ModelAttribute("allCurrencies")
    public Map<String, String> allCurrencies() {
        return this.currencyExchangeProvider.retrieveAllCurrencies();
    }

}
