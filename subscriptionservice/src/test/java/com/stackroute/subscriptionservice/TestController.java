package com.stackroute.subscriptionservice;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.stackroute.favoriteservice.model.FavModel;
import com.stackroute.subscriptionservice.Controller.SubscriptionController;
import com.stackroute.subscriptionservice.model.SubscriptionUser;
import com.stackroute.subscriptionservice.service.SubscriptionService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@InjectMocks
	private SubscriptionController subscriptionController;
	
	@Mock
	private SubscriptionService subscriptionService;
	
	@Autowired
	MockMvc mockMvc;
	
	private List<String> list1 = new ArrayList<>();
	private List<String> list2 = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
	}
	
	@Test
	public void testGetAllSubscriptionSuccess() throws Exception {
		
		list1.add("5days");
		list1.add("10days");
		SubscriptionUser subs1 = new SubscriptionUser("u1", list1);
		
		when(subscriptionService.getAllSubscription(subs1.getUserId())).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sub/u1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	public void testGetAllSubscriptionFailure() throws Exception {
		
		list1.clear();
		SubscriptionUser subs1 = new SubscriptionUser("u1", list1);
		when(subscriptionService.getAllSubscription(subs1.getUserId())).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sub/u1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
	@Test
	public void testAddSubcriptionSuccess() throws Exception {
		
		list1.add("5days");
		list1.add("10days");
		SubscriptionUser subs1 = new SubscriptionUser("u1", list1);
		list1.add("30days");
		when(subscriptionService.subscribe("u1", "30days")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe/u1/30days").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}
	@Test
	public void testAddSubcriptionFailure() throws Exception {
		
		list1.add("5days");
		list1.add("10days");
		SubscriptionUser sub1 = new SubscriptionUser("u1", list1);
		list1.add("30days");
		when(subscriptionService.subscribe("u1", "30days")).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe/u1/30days").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());
		
	}
	
	@Test
	public void testDeleteSubcriptionSuccess() throws Exception {
		list1.add("5days");
		list1.add("10days");
		SubscriptionUser sub1 = new SubscriptionUser("u1", list1);
		list1.remove(1);
		when(subscriptionService.unsubscribe("u1", "10days")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sub/unsubscribe/u1/10days").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	
	@Test
	public void testDeleteSubscriptionFailure() throws Exception {
		list1.add("5days");
		list1.add("10days");
		
		list1.remove(1);
		when(subscriptionService.unsubscribe("u1", "10days")).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sub/unsubscribe/u1/10days").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
}
