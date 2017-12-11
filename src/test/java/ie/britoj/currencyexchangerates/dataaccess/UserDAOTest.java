package ie.britoj.currencyexchangerates.dataaccess;

import ie.britoj.currencyexchangerates.configurations.WebMvcConfiguration;
import ie.britoj.currencyexchangerates.models.Address;
import ie.britoj.currencyexchangerates.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@Transactional
@TestPropertySource("classpath:application-test.properties")
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    User user;

    @Before
    public void setUp(){
        Address address = new Address("Some street name", "someZipCode", "someCity", "someCountry");
        this.user = new User("email_test@email_provider.com", "asd", new Date(), address);
        userDAO.create(user);
    }

    @Test
    public void createUserDatabaseTest(){
        assertThat(this.user.getId()).isGreaterThan(0);


    }

    @Test
    public void findUserByIdTest(){

        User userFromDb = userDAO.findById(this.user.getId());
        assertThat(userFromDb).isNotNull();
        assertThat(userFromDb.getEmail()).isNotEmpty().isEqualTo(this.user.getEmail());
        assertThat(userFromDb.getPassword()).isNotEmpty().isEqualTo(this.user.getPassword());
        assertThat(userFromDb.getDateOfBirth()).isEqualTo(this.user.getDateOfBirth());
        assertThat(userFromDb.getAddress()).isNotNull();
        assertThat(userFromDb.getAddress().getStreet()).isNotEmpty().isEqualTo(this.user.getAddress().getStreet());
        assertThat(userFromDb.getAddress().getZipCode()).isNotEmpty().isEqualTo(this.user.getAddress().getZipCode());
        assertThat(userFromDb.getAddress().getCity()).isNotEmpty().isEqualTo(this.user.getAddress().getCity());
        assertThat(userFromDb.getAddress().getCountry()).isNotEmpty().isEqualTo(this.user.getAddress().getCountry());

    }

    @Test
    public void updateUserTest(){
        User userFromDb = userDAO.findById(this.user.getId());
        String newUserEmail = "some_new_user_email@some_new_email_provider.com";
        userFromDb.setEmail(newUserEmail);
        userDAO.update(userFromDb);
        User updatedUser = userDAO.findById(this.user.getId());
        assertThat(updatedUser.getEmail()).isEqualTo(newUserEmail);


    }

    @Test
    public void deleteUserTest() {
        userDAO.delete(this.user);
        User userFromDb = userDAO.findById(this.user.getId());
        assertThat(userFromDb).isNull();
    }

    @Test
    public void findByEmailTest(){
        User userFromDb = userDAO.findByEmail(user.getEmail());
        assertThat(userFromDb).isNotNull();
        assertThat(userFromDb.getId()).isGreaterThan(0);
    }


}
