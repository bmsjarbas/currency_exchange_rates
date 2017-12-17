package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.validators.SignUpValidator;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@Controller
@RequestMapping("/signup")
@Transactional
public class SignUpController {

    @Autowired
    UserManager userManager;
    @Autowired
    SignUpValidator signUpValidator;

    @GetMapping()
    public ModelAndView getSignUp(){
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        return new ModelAndView("signup-form", "signUp", signUpViewModel);
    }

    @PostMapping
    public String postSignUp(@ModelAttribute("signUp") SignUpViewModel signUp,
                             BindingResult bindingResult){

        signUpValidator.validate(signUp, bindingResult);
        if(bindingResult.hasErrors()){
            return "signup-form";
        }

        userManager.create(signUp.createUser());
        return "redirect:/signin?userCreated";

    }

    @ModelAttribute("allCountries")
    public ArrayList<String> getCountriesList() {
        String[] isoCountries = Locale.getISOCountries();
        ArrayList<String> countriesList = new ArrayList<String>(isoCountries.length);
        //countriesList.add("Select Country");
        Arrays.stream(isoCountries)
                .forEach(country ->
                        countriesList.add(new Locale("en", country).getDisplayCountry()));
        return countriesList;

    }
}