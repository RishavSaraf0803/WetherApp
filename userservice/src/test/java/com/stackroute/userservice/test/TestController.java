package com.stackroute.userservice.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.userservice.controller.UserController;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.services.UserServices;


@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserServices userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void testRegisterUserSuccess() throws Exception {
		
		UserModel user = new UserModel();
        user.setUserId("Jai");
        user.setUserName("Jai");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
		
		when(userService.registerUser(user)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testRegisterUserFailure() throws Exception {
		
		UserModel user1 = new UserModel();
        user1.setUserId("Jai");
        user1.setUserName("Jai");
        user1.setUserPassword("123456");
        user1.setUserAddedDate(new Date());
		
		when(userService.registerUser(user1)).thenReturn(user1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());

	}
	
	@Test
	public void testLoginUserSuccess() throws Exception {
		
		UserModel user2 = new UserModel();
        user2.setUserId("Jai");
        user2.setUserName("Jai");
        user2.setUserPassword("123456");
        user2.setUserAddedDate(new Date());
		
		when(userService.findByUserNameAndPassword(user2.getUserName(), user2.getUserPassword())).thenReturn(user2);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testLoginUserFailure() throws Exception {
		
		UserModel user3 = new UserModel();
        user3.setUserId("Jai");
        user3.setUserName("Jai");
        user3.setUserPassword("98765");
        user3.setUserAddedDate(new Date());
		
		when(userService.findByUserNameAndPassword(user3.getUserName(), user3.getUserPassword())).thenReturn(user3);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isUnauthorized());

	}
	
	@Test
	public void getUserByIdTestSuccess() throws Exception {
		
		UserModel user4 = new UserModel();
        user4.setUserId("Jai");
        user4.setUserName("Jai");
        user4.setUserPassword("123456");
        user4.setUserAddedDate(new Date());
        
        Optional<UserModel> optUser = Optional.of(user4);
        	
		when(userService.getUserById(user4.getUserId())).thenReturn(optUser);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void getUserByIdTestFailure() throws Exception {
		
		UserModel user5 = new UserModel();
		user5.setUserId(null);
        
        Optional<UserModel> optUser = Optional.of(user5);
        	
		when(userService.getUserById(user5.getUserId())).thenReturn(optUser);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());

	}
}
