package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.web.viewmodels.SignInViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("/signin")
public class SignInController{

    @GetMapping
    public ModelAndView getSignIn(){
        SignInViewModel signInViewModel = new SignInViewModel();
        ModelAndView modelAndView = new ModelAndView("signInForm",
                "signIn", signInViewModel);
        return modelAndView;

    }
}
