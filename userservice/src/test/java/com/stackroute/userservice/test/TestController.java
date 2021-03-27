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

	private List<String> list1 = new ArrayList<>();
	private List<String> list2 = new ArrayList<>();
	
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
		
		UserModel user = new UserModel();
        user.setUserId("Jai");
        user.setUserName("Jai");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
        userService.registerUser(user);
		
		when(userService.registerUser(user)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());

	}
	
	@Test
	public void testLoginUserSuccess() throws Exception {
		
		UserModel user = new UserModel();
        user.setUserId("Jai");
        user.setUserName("Jai");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
		
		when(userService.findByUserIdAndPassword(user.getUserName(), user.getUserPassword())).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testLoginUserFailure() throws Exception {
		
		UserModel user = new UserModel();
        user.setUserId("Jai");
        user.setUserName("Jai");
        user.setUserPassword("98765");
        user.setUserAddedDate(new Date());
		
		when(userService.findByUserIdAndPassword(user.getUserName(), user.getUserPassword())).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isUnauthorized());

	}
	
	@Test
	public void getUserByIdTestSuccess() throws Exception {
		
		UserModel user = new UserModel();
        user.setUserId("Jai");
        user.setUserName("Jai");
        user.setUserPassword("123456");
        user.setUserAddedDate(new Date());
        
        Optional<UserModel> optUser = Optional.of(user);
        	
		when(userService.getUserById(user.getUserId())).thenReturn(optUser);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void getUserByIdTestFailure() throws Exception {
		
		UserModel user = new UserModel();
        
        Optional<UserModel> optUser = Optional.of(user);
        	
		when(userService.getUserById(user.getUserId())).thenReturn(optUser);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());

	}
}
