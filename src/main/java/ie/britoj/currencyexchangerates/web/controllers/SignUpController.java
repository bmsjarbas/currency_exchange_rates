package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping()
    public ModelAndView index(){
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        return new ModelAndView("signUpForm", "signUp", signUpViewModel);
    }
}