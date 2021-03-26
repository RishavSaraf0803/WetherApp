package com.stackroute.userservice;

import java.util.Date;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.repositroy.UserRepository;
import com.stackroute.userservice.services.UserServicesImpl;

@SpringBootTest
class UserServiceApplicationTests {

    @Mock
    private UserRepository autheticationRepository;

    private UserModel user;
    @InjectMocks
    private UserServicesImpl authenticationService;

    Optional<UserModel> optional;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new UserModel();
        user.setUserId("Jhon123");
        user.setUserName("Jhon123");
        //user.setLastName("Smith");
        user.setUserImage("Admin");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {

        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    }


    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUserFailure() throws UserAlreadyExistsException {

        Mockito.when(autheticationRepository.findById("Jhon123")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    }

    @Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
        Mockito.when(autheticationRepository.findByUserIdAndUserPassword("Jhon123", "123456")).thenReturn(user);
        UserModel fetchedUser = authenticationService.findByUserIdAndPassword("Jhon123", "123456");
        Assert.assertEquals("Jhon123", fetchedUser.getUserId());
    }
}
