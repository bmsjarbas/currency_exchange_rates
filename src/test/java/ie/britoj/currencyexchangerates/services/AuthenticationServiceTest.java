package ie.britoj.currencyexchangerates.services;

import ie.britoj.currencyexchangerates.configurations.WebMvcConfiguration;
import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.models.Address;
import ie.britoj.currencyexchangerates.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@Transactional
@TestPropertySource("classpath:application-test.properties")
public class AuthenticationServiceTest {

    @Autowired
    UserDetailsService authenticationService;

    @Autowired
    UserDAO userDAO;

    private User user;

    @Before
    public void setUp(){
        Address address = new Address("Some street name", "someZipCode", "someCity", "someCountry");
        this.user = new User("email_test@email_provider.com", "asd", LocalDate.now(), address);
        userDAO.create(user);
    }

    @Test
    public void loadUserByUsernameReturnValidUserTest(){
        org.springframework.security.core.userdetails.UserDetails springSecurityUser = this.authenticationService.
                loadUserByUsername(user.getEmail());
        assertThat(springSecurityUser.getUsername()).isEqualTo(user.getEmail());
    }


}
