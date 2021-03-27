package com.stackroute.userservice.test;

import org.junit.jupiter.api.Test;
//import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
    @Test
    void contextLoads() {
    	
    }

    @Test
    void testC() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	
    }
}





