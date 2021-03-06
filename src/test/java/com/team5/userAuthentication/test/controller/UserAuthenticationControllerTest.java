package com.team5.userAuthentication.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.userAuthentication.controller.UserAuthenticationController;
import com.team5.userAuthentication.exeptions.*;
import com.team5.userAuthentication.model.*;
import com.team5.userAuthentication.service.*;
import com.team5.userAuthentication.test.controller.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAuthenticationService authenticationService;

    private User user;

    @InjectMocks
    private UserAuthenticationController authenticationController;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        user = new User();
        user.setUserId("Jhon123");
//        user.setFirstName("Jhon");
//        user.setLastName("Smith");
        user.setUserPassword("123456");
        user.setEmail("giriallada99@gmail.com");
//        user.setUserRole("Admin");
//        user.setUserAddedDate(new Date());


    }

    @Test
    public void testRegisterUser() throws Exception {
        user = new User();
        user.setUserId("Jhon123");
//        user.setFirstName("Jhon");
//        user.setLastName("Smith");
        user.setUserPassword("123456");
        user.setEmail("giriallada99@gmail.com");
        Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void testLoginUser() throws Exception {
        user = new User();
        user.setUserId("Jhon123");
//        user.setFirstName("Jhon");
//        user.setLastName("Smith");
        user.setUserPassword("123456");
        user.setEmail("giriallada99@gmail.com");


        String userId = "Jhon123";
        String password = "123456";


        Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
        Mockito.when(authenticationService.findByUserIdAndPassword(userId, password)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
}
