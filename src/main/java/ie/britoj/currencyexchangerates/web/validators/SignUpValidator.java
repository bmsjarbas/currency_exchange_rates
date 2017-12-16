package ie.britoj.currencyexchangerates.web.validators;

import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.services.UserManager;
import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Component
public class SignUpValidator implements Validator{

    @Autowired
    UserDAO userDAO;


    public boolean supports(Class<?> aClass) {
        return SignUpViewModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

        SignUpViewModel signUpViewModel = (SignUpViewModel) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetAddress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty");


        if(!errors.hasFieldErrors("email") && !emailIsValid(signUpViewModel.getEmail())){
            errors.rejectValue("email", "signUpForm.invalidEmail");
        }

        if(userDAO.findByEmail(signUpViewModel.getEmail()) != null){
            errors.rejectValue("email", "signUpForm.userEmailAlreadyExists");
        }

        if(signUpViewModel.getDateOfBirth() == null){
            errors.rejectValue("dateOfBirth", "NotEmpty");
        }

        if(!errors.hasFieldErrors("password") && !hasPasswordMinimumRequirements(signUpViewModel.getConfirmPassword())){
                errors.rejectValue("password", "signUpForm.passwordMinimumRequirement");
        }

        if(!errors.hasFieldErrors("confirmPassword") && !signUpViewModel.getConfirmPassword().equals(signUpViewModel.getPassword())){
            errors.rejectValue("confirmPassword", "signUpForm.passwordDoesNotMatch");
        }
        
        if(signUpViewModel.getDateOfBirth() != null && !hasMinimumAge(signUpViewModel.getDateOfBirth())){
            errors.rejectValue("dateOfBirth", "signUpForm.minimumAge");
        }
    }

    private boolean hasMinimumAge(LocalDate dateOfBirth) {
        LocalDate dateNow = LocalDate.now();
        Period age = Period.between(dateOfBirth, dateNow);
        return age.getYears() >= 16;

    }
    private boolean hasPasswordMinimumRequirements(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean lengthFlag = password.length() >= 8;
        boolean specialCharFlag = false;

        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {

                lowerCaseFlag = true;
            }
            else if(!Character.isDigit(ch) && !Character.isLetter(ch)){
                specialCharFlag  = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag && lengthFlag && specialCharFlag) {
                return true;
            }
        }
        return false;
    }


    private boolean emailIsValid(String email){
        String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return  pattern.matcher(email).matches();
    }
}
