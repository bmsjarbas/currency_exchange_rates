package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    UserManager userManager;

    @GetMapping()
    public ModelAndView index(){
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        return new ModelAndView("signUpForm", "signUp", signUpViewModel);
    }

    @PostMapping
    public String create(@RequestBody SignUpViewModel viewModel){
        System.out.println(viewModel.getEmail());
        userManager.create(viewModel.createUser());
        return "redirect:/signup/confirmation";

    }


    @GetMapping("/confirmation")
    public String confirmation(){
        return "signupConfirmation";
    }
}