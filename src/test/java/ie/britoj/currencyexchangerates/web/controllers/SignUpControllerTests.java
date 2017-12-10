package ie.britoj.currencyexchangerates.web.controllers;

import ie.britoj.currencyexchangerates.web.viewmodels.SignUpViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpControllerTests {
    private MockMvc mockMvc;
    @InjectMocks
    private SignUpController signUpController;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(signUpController)
                .build();

    }

    @Test
    public void testGetShouldReturnSignUpViewModelForm() throws Exception {
        MvcResult result = mockMvc.perform(get("/signup"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = result.getModelAndView();
        assertEquals("signUpForm", modelAndView.getViewName());
        assertEquals(SignUpViewModel.class, modelAndView.getModel().get("signUp").getClass());
    }

}