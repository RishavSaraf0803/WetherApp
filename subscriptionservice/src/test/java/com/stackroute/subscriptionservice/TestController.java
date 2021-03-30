package com.stackroute.subscriptionservice;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
	
	@Mock
	private SubscriptionService subscriptionService;
	@Mock
	Feature fet;
	@Autowired
	MockMvc mockMvc;
	
	private List<Feature> list1 = new ArrayList<>();

	
	Feature fet1 = new Feature();
	Feature fet2 = new Feature();
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
		fet = new Feature();
		fet.setFeatureName("5days");
		fet.setFeatureSubscribeBy("raju");
		fet.setFeaturePrice("5000");
		list1.add(fet);
	}
	
	@Test
	public void testGetAllSubscriptionSuccess() throws Exception {
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet2.setFeatureName("10days");
		fet2.setFeatureSubscribeBy("raju");
		fet2.setFeaturePrice("5000");
		list1.add(fet1);
		list1.add(fet2);
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
		
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet1.setFeatureSubscribeDate(new Date());
		fet2.setFeatureName("10days");
		fet2.setFeatureSubscribeBy("raju");
		fet2.setFeaturePrice("5000");
		fet2.setFeatureSubscribeDate(new Date());
		Feature fet3 = new Feature();
		fet3.setFeatureName("15days");
		fet3.setFeatureSubscribeBy("raju");
		fet3.setFeaturePrice("10000");
		fet3.setFeatureSubscribeDate(new Date());
		list1.add(fet1);
		list1.add(fet2);
		
		SubscriptionUser subs1 = new SubscriptionUser("raju", list1);
		list1.add(fet3);
		when(subscriptionService.subscribe(fet)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}
	@Test
	public void testAddSubcriptionFailure() throws Exception {
		

		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet2.setFeatureName("10days");
		fet2.setFeatureSubscribeBy("raju");
		fet2.setFeaturePrice("5000");
		Feature fet3 = new Feature();
		fet3.setFeatureName("15days");
		fet3.setFeatureSubscribeBy("raju");
		fet3.setFeaturePrice("10000");
		SubscriptionUser sub1 = new SubscriptionUser("raju", list1);
		list1.add(fet3);
		when(subscriptionService.subscribe(fet3)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sub/subscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());
		
	}
	
	@Test
	public void testDeleteSubcriptionSuccess() throws Exception {

		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet2.setFeatureName("10days");
		fet2.setFeatureSubscribeBy("raju");
		fet2.setFeaturePrice("5000");
		list1.add(fet1);
		list1.add(fet2);
		
		list1.remove(1);
		when(subscriptionService.unsubscribe(fet2)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sub/unsubscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
	
	@Test
	public void testDeleteSubscriptionFailure() throws Exception {
		
		fet1.setFeatureName("5days");
		fet1.setFeatureSubscribeBy("raju");
		fet1.setFeaturePrice("5000");
		fet2.setFeatureName("10days");
		fet2.setFeatureSubscribeBy("raju");
		fet2.setFeaturePrice("5000");
		list1.add(fet1);
		list1.add(fet2);
		list1.remove(1);
		when(subscriptionService.unsubscribe(fet2)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/sub/unsubscribe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
}
