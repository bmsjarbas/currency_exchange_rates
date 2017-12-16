package ie.britoj.currencyexchangerates.web.viewmodels;

import ie.britoj.currencyexchangerates.models.Address;
import ie.britoj.currencyexchangerates.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class SignUpViewModel {
    private String email;
    private String password;
    private String confirmPassword;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private String streetAddress;
    private String zipCode;
    private String city;
    private String country;
    /*Just to keep it simple. At the moment we just have only one use for this thing */
    private ArrayList<String> countriesList;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public User createUser() {
        Address address = new Address(this.streetAddress, this.zipCode, this.city, this.country);
        return new User(this.email, this.password, this.dateOfBirth, address);
    }

    public ArrayList<String> getCountriesList() {
        String[] isoCountries = Locale.getISOCountries();
        countriesList = new ArrayList<String>(isoCountries.length);
        //countriesList.add("Select Country");
        Arrays.stream(isoCountries)
                .forEach(country ->
                        countriesList.add(new Locale("en", country).getDisplayCountry()));
        return countriesList;

    }
}
