package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User email:"+ email+ " not found");
        }
        ArrayList<GrantedAuthority>  authorities =
                new ArrayList<GrantedAuthority>(Arrays.asList(
                        new SimpleGrantedAuthority("simple_role")));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), authorities);
    }
}
