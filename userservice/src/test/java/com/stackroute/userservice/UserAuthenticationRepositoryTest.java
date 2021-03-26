package com.stackroute.userservice;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.repositroy.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class UserAuthenticationRepositoryTest {

    @Autowired
    private UserRepository autheticationRepository;

    private UserModel user;


    @Before
    public void setUp() throws Exception {
        user = new UserModel();
        user.setUserId("Jhon123");
        user.setUserName("Jhon123");
        //user.setLastName("Smith");
        user.setUserImage("Admin");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
    }

    @After
    public void tearDown() throws Exception {
        autheticationRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        autheticationRepository.save(user);
        UserModel object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }

    @Test
    public void testLoginUserSuccess() {
        autheticationRepository.save(user);
        UserModel object = autheticationRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(), object.getUserId());
    }
}
