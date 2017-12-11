package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserDAO userDAO;

    public Long create(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.create(user);
        return user.getId();
    }

}
