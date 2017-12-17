package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.dataaccess.QueryHistoryDAO;
import ie.britoj.currencyexchangerates.models.QueryHistory;
import ie.britoj.currencyexchangerates.web.viewmodels.HistoryItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    QueryHistoryDAO queryHistoryDAO;

    @GetMapping
    public ModelAndView get(){

        List<QueryHistory> history = queryHistoryDAO.historyByUser(loggedUserMail());
        List<HistoryItemViewModel> viewModel = parseHistoryList(history);
        return new ModelAndView("history", "queries", viewModel);

    }

    private List<HistoryItemViewModel> parseHistoryList(List<QueryHistory> queryFromDb){
        List<HistoryItemViewModel> historyItemViewModelList = new ArrayList<>();
        queryFromDb.forEach(item-> historyItemViewModelList.add(new HistoryItemViewModel(item.getBaseCurrency(), item.getDate())));
        return historyItemViewModelList;
    }

    private String loggedUserMail(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return ((User)securityContext.getAuthentication().getPrincipal()).getUsername();

    }

}
