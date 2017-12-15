package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Long create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
        return user.getId();
    }

}
