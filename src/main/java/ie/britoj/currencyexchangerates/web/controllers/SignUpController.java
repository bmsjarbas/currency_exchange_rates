package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping()
    public ModelAndView index(){
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        return new ModelAndView("signUpForm", "signup", signUpViewModel);
    }

    @PostMapping
    public String create(@ModelAttribute("signup") SignUpViewModel signUp){
        System.out.println(signUp.getEmail());
        userManager.create(signUp.createUser());
        return "redirect:/signup/confirmation";

    }


    @GetMapping("/confirmation")
    public String confirmation(){
        return "signupConfirmation";
    }
}