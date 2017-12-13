package ie.britoj.currencyexchangerates.web.validators;

import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator{

    public boolean supports(Class<?> aClass) {
        return SignUpViewModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        SignUpViewModel signUpViewModel = (SignUpViewModel) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetAddress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty");


    }
}
