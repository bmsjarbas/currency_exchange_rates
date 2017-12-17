package ie.britoj.currencyexchangerates.web.validators;

import ie.britoj.currencyexchangerates.web.viewmodels.SearchViewModel;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SearchValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return SearchViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SearchViewModel searchViewModel = (SearchViewModel) o;



    }
}
