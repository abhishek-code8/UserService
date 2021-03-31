package com.team5.userAuthentication.test.repository;

import com.team5.userAuthentication.model.*;
import com.team5.userAuthentication.repository.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Query;
import java.util.Date;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class UserAuthenticationRepositoryTest {

    @Autowired
    private UserAutheticationRepository autheticationRepository;

    private User user;


    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("Jhon123");
//        user.setFirstName("Jhon123");
//        user.setLastName("Smith");
//        user.setUserRole("Admin");
        user.setUserPassword("123456");
        user.setEmail("giriallada99@gmail.com");
//        user.setUserAddedDate(new Date());
    }

    @After
    public void tearDown() throws Exception {
        autheticationRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
    	  user = new User();
          user.setUserId("Jhon123");
//          user.setFirstName("Jhon123");
//          user.setLastName("Smith");
//          user.setUserRole("Admin");
          user.setUserPassword("123456");
          user.setEmail("giriallada99@gmail.com");
        autheticationRepository.save(user);
        User object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }

    @Test
    public void testLoginUserSuccess() {
    	  user = new User();
          user.setUserId("Jhon123");
//          user.setFirstName("Jhon123");
//          user.setLastName("Smith");
//          user.setUserRole("Admin");
          user.setUserPassword("123456");
          user.setEmail("giriallada99@gmail.com");
        autheticationRepository.save(user);
        User object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }
}
