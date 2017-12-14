package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.validators.SignUpValidator;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("signUpForm", "signUp", signUpViewModel);
    }

    @PostMapping
    public String postSignUp(@ModelAttribute("signUp") SignUpViewModel signUp, BindingResult bindingResult
    ){

        signUpValidator.validate(signUp, bindingResult);
        if(bindingResult.hasErrors()){
            return "signUpForm";
        }

        userManager.create(signUp.createUser());
        return "redirect:/signup/confirmation";

    }

    @GetMapping("/confirmation")
    public String getConfirmation(){
        return "signupConfirmation";
    }
}