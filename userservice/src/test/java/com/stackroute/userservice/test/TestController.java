package com.stackroute.userservice.test;

import static org.mockito.Mockito.when;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.userservice.controller.UserController;
import com.stackroute.userservice.messages.JwtResponse;
import com.stackroute.userservice.messages.LoginForm;
import com.stackroute.userservice.messages.SignUpForm;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.services.UserDetailsServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserDetailsServiceImpl userService;
	private ResponseEntity<String> regResponse;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void testRegisterUserSuccess() throws Exception {
		
		//UserModel user = new UserModel();
		SignUpForm form = new SignUpForm();
		form.setName("Jaivrat");
		form.setUsername("jai");
		form.setPassword("123456789");
		form.setEmail("jai@gmail.com");
//        user.setUserId(1);
//        user.setUserName("Jai");
//        user.setUserPassword("123456");
//        user.setUserEmail("jai@gmail.com");
        //user.setUserAddedDate(new Date());
		
//		when(userController.registerUser(form)).thenReturn(regResponse);
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testRegisterUserFailure() throws Exception {
		
		SignUpForm form = new SignUpForm();
		form.setName("Jaivrat");
		form.setUsername("jai");
		form.setPassword("123456789");
		form.setEmail("jai@gmail.com");
		
//		UserModel user1 = new UserModel();
//        user1.setUserId(1);
//        user1.setUserName("Jai");
//        user1.setUserPassword("123456");
//        user1.setUserEmail("jai@gmail.com");
        //user1.setUserAddedDate(new Date());
		
//		when(userController.registerUser(form)).thenReturn(regResponse);
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isConflict());

	}
	
//	@Test
//	public void testLoginUserSuccess() throws Exception {
//		
//		LoginForm form = new LoginForm();
//		form.setUsername("jai");
//		form.setPassword("123456789");
////		UserModel user2 = new UserModel();
////        user2.setUserId(1);
////        user2.setUserName("Jai");
////        user2.setUserPassword("123456");
////        user2.setUserEmail("jai@gmail.com");
//        //user2.setUserAddedDate(new Date());
//		
//		//when(userService.findByUserNameAndPassword(user2.getUserName(), user2.getUserPassword())).thenReturn(user2);
//		when(userController.authenticateUser(form)).thenReturn(new JwtResponse(token));
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isOk());
//
//	}
	
//	@Test
//	public void testLoginUserFailure() throws Exception {
//		
//		UserModel user3 = new UserModel();
//        user3.setUserId(1);
//        user3.setUserName("Jai");
//        user3.setUserPassword("98765");
//        //user3.setUserAddedDate(new Date());
//        user3.setUserEmail("jai@gmail.com");
//		
//		when(userService.findByUserNameAndPassword(user3.getUserName(), user3.getUserPassword())).thenReturn(user3);
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isUnauthorized());
//
//	}
	
//	@Test
//	public void getUserByIdTestSuccess() throws Exception {
//		
//		UserModel user4 = new UserModel();
//        user4.setUserId(1);
//        user4.setUserName("Jai");
//        user4.setUserPassword("123456");
//        //user4.setUserAddedDate(new Date());
//        user4.setUserEmail("jai@gmail.com");
//        
//        Optional<UserModel> optUser = Optional.of(user4);
//        	
//		when(userService.getUserById(user4.getUserId())).thenReturn(optUser);
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isOk());
//
//	}
//	
//	@Test
//	public void getUserByIdTestFailure() throws Exception {
//		
//		UserModel user5 = new UserModel();
//		user5.setUserId(0);
//        
//        Optional<UserModel> optUser = Optional.of(user5);
//        	
//		when(userService.getUserById(user5.getUserId())).thenReturn(optUser);
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/Jai").contentType(MediaType.APPLICATION_JSON))
//					.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}
}
