package com.team5.userAuthentication.test.service;

import com.team5.userAuthentication.exeptions.*;
import com.team5.userAuthentication.model.*;
import com.team5.userAuthentication.repository.*;
import com.team5.userAuthentication.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

public class UserAuthenticationServiceTest {

    @Mock
    private UserAutheticationRepository autheticationRepository;

    private User user;
    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;

    Optional<User> optional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("Jhon123");
        
        user.setUserPassword("123456");
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {
    	user = new User();
        user.setUserId("Jhon123");
        
        user.setUserPassword("123456");
        optional = Optional.of(user);

        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    }


    @Test
    public void testSaveUserFailure() throws UserAlreadyExistsException {
//    	user = new User();
//        user.setUserId("Jhon123");
//        
//        user.setUserPassword("123456");
//        optional = Optional.of(user);
        Assertions.assertThrows(UserAlreadyExistsException.class, ()->{


        Mockito.when(autheticationRepository.findById("Jhon123")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    });
    }

    @Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
//    	user = new User();
//        user.setUserId("Jhon123");
//        
//        user.setUserPassword("123456");
        Mockito.when(autheticationRepository.findByUserIdAndUserPassword("Jhon123", "123456")).thenReturn(user);
        User fetchedUser = authenticationService.findByUserIdAndPassword("Jhon123", "123456");
        Assert.assertEquals("Jhon123", fetchedUser.getUserId());
    }
}
