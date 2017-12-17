package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;
import ie.britoj.currencyexchangerates.services.CurrencyExchangeProvider;
import ie.britoj.currencyexchangerates.web.validators.SearchValidator;
import ie.britoj.currencyexchangerates.web.viewmodels.SearchViewModel;
import ie.britoj.currencyexchangerates.web.viewmodels.SignInViewModel;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CurrencyExchangeProvider currencyExchangeProvider;
    @Autowired
    SearchValidator searchValidator;

    @GetMapping
    public ModelAndView index(RedirectAttributes redirectAttributes){
        Map<String, String> allCurrencies = currencyExchangeProvider.retrieveAllCurrencies();

        SearchViewModel searchViewModel = (SearchViewModel)redirectAttributes.getFlashAttributes().get("search");
        if(searchViewModel == null){
            searchViewModel =  new SearchViewModel(allCurrencies);
        }


        ModelAndView modelAndView = new ModelAndView();
        return new ModelAndView("index", "search", searchViewModel);
    }


    @PostMapping
    public String search(
        @ModelAttribute("search") SearchViewModel searchViewModel,
        BindingResult bindingResult, RedirectAttributes redirectAttributes){
        searchValidator.validate(searchViewModel, bindingResult);

        ExchangeRatesQueryResult result = currencyExchangeProvider
                .retrieveHistoricalCurrencyExchangeRate(searchViewModel.getCurrency(), searchViewModel.getDate());

        if(result.hasError()){
            bindingResult.rejectValue("currency", result.getError());
            return "index";
        }
        redirectAttributes.addFlashAttribute("search", searchViewModel);
        return "redirect:/";

    }
}
