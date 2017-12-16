package ie.britoj.currencyexchangerates.configurations;

import ie.britoj.currencyexchangerates.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.authenticationService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
               http.authorizeRequests()
                       .antMatchers("/resources/**", "/webjars/**","/static/**" ,"/signup", "/signup/confirmation")
                       .permitAll()
                       .anyRequest().authenticated()
                       .and()
                       .formLogin()
                       .loginPage("/signin")
                       .defaultSuccessUrl("/").permitAll()
                       .and()
                       .logout()
                       .logoutUrl("/signout").logoutSuccessUrl("/signin?signout").permitAll();

    }

}
