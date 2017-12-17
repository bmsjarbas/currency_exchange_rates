package ie.britoj.currencyexchangerates.aspects;

import ie.britoj.currencyexchangerates.dataaccess.DAO;
import ie.britoj.currencyexchangerates.dataaccess.UserDAO;
import ie.britoj.currencyexchangerates.models.QueryHistory;
import ie.britoj.currencyexchangerates.models.Rate;
import ie.britoj.currencyexchangerates.openexchangerates.ExchangeRatesQueryResult;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogCurrencyExchangeResult {
    @Autowired
    DAO<QueryHistory> queryHistoryDAO;
    @Autowired
    UserDAO userDAO;

    @AfterReturning(
            pointcut = "execution(* ie.britoj.currencyexchangerates.services.CurrencyExchangeProvider.retrieveHistoricalCurrencyExchangeRate(..))",
            returning="result")
    public void logQueryResult(ExchangeRatesQueryResult result) {
        QueryHistory queryHistory = new QueryHistory();
        queryHistory.setUser(getLoggedUser());
        queryHistory.setBaseCurrency(result.getBase());
        if(!result.hasError()) {
            result.getRates().forEach((currency, price) -> queryHistory.getRates().add(new Rate(currency, price)));
        }
        queryHistoryDAO.create(queryHistory);

    }

    public ie.britoj.currencyexchangerates.models.User getLoggedUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication() != null) {
            User user = (User) securityContext.getAuthentication().getPrincipal();
            return userDAO.findByEmail(user.getUsername());
        }
        return null;
    }

}
