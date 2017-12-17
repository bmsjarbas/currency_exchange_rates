package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.configurations.WebMvcConfiguration;
import ie.britoj.currencyexchangerates.web.viewmodels.SearchViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@Transactional
@TestPropertySource("classpath:application-test.properties")
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .build();

    }

    @Test
    public void testGetShouldReturnIndexView() throws Exception {
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = result.getModelAndView();
        assertThat(modelAndView.getViewName())
                .isEqualTo("index");
    }


    @Test
    public void testPostInvalidBaseShouldReturnViewModelWithError() throws Exception {

        SearchViewModel searchViewModel = new SearchViewModel();
        searchViewModel.setCurrency("ABA");
        searchViewModel.setDate(LocalDate.now().minusDays(3));

        MvcResult result = mockMvc.perform(post("/")
                .flashAttr("search", searchViewModel))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualTo("index");


    }
}
