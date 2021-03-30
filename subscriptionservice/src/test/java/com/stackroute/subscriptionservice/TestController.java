package com.stackroute.subscriptionservice;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.stackroute.subscriptionservice.Controller.SubscriptionController;
import com.stackroute.subscriptionservice.model.Feature;
import com.stackroute.subscriptionservice.model.SubscriptionUser;
import com.stackroute.subscriptionservice.service.SubscriptionService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@InjectMocks
	private SubscriptionController subscriptionController;
	
	@MockBean
	private SubscriptionService subscriptionService;
	@MockBean
	private Feature fet;
	@Autowired
	MockMvc mockMvc;
	
	private List<Feature> list1;

	
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();

	}
	
	@Test
	public void testGetAllSubscriptionSuccess() throws Exception {

		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		list1 = new ArrayList<>();
		list1.add(fet);
	
		SubscriptionUser subs1 = new SubscriptionUser("raju", list1);
		
		when(subscriptionService.getAllSubscription("raju")).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sub?userId=raju").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());

	}
	@Test
	public void testGetAllSubscriptionFailure() throws Exception {
		list1 = new ArrayList<>(); 
		list1.clear();
		SubscriptionUser subs1 = new SubscriptionUser("raju", list1);
		when(subscriptionService.getAllSubscription(subs1.getUserId())).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sub/?userId=raju").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound())
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testAddSubcriptionSuccess() throws Exception {
		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		Feature fet1 = new Feature();
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet1.setFeatureSubscribeDate(new Date());
		list1 = new ArrayList<>();
		list1.add(fet);
		
		
		
		SubscriptionUser subs1 = new SubscriptionUser("raju", list1);
		
		when(subscriptionService.subscribe(any())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe",fet1).contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}
	

	@Test
	public void testAddSubcriptionFailure() throws Exception {
		
		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		Feature fet1 = new Feature();
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet1.setFeatureSubscribeDate(new Date());
		list1 = new ArrayList<>();
		list1.add(fet);
		SubscriptionUser sub1 = new SubscriptionUser("raju", list1);
		list1.add(fet1);
		when(subscriptionService.subscribe(any())).thenReturn(false);
	mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());
		
	}
	
	@Test
	public void testDeleteSubcriptionSuccess() throws Exception {
		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		Feature fet1 = new Feature();
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet1.setFeatureSubscribeDate(new Date());
		list1 = new ArrayList<>();
		list1.add(fet);
		list1.add(fet1);
		
		
		when(subscriptionService.unsubscribe(any())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/unsubscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	
	@Test
	public void testDeleteSubscriptionFailure() throws Exception {
		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		Feature fet1 = new Feature();
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet1.setFeatureSubscribeDate(new Date());
		list1 = new ArrayList<>();
		list1.add(fet);
		list1.add(fet1);
		list1.remove(1);
		when(subscriptionService.unsubscribe(any())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/unsubscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
}
