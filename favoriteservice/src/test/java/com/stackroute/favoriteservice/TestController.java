package com.stackroute.favoriteservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
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

import com.stackroute.favoriteservice.controller.FavController;
import com.stackroute.favoriteservice.model.FavModel;
import com.stackroute.favoriteservice.service.FavService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {
	
	@InjectMocks
	private FavController favController;
	
	@Mock
	private FavService favService;
	
	
	@Autowired
	private MockMvc mockMvc;

	private List<String> list1 = new ArrayList<>();
	private List<String> list2 = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(favController).build();
	}
	

	@Test
	public void testGetAllCitiesSuccess() throws Exception {
		
		list1.add("pune");
		list1.add("beed");
		FavModel fav1 = new FavModel("u1", list1);
		
		when(favService.getAllCities(fav1.getUserId())).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fav/u1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testGetAllCitiesFailure() throws Exception {
		
		list1.clear();
		FavModel fav1 = new FavModel("u1", list1);
		when(favService.getAllCities(fav1.getUserId())).thenReturn(list1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fav/u1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
	
	@Test
	public void testAddCitySuccess() throws Exception {
		
		list1.add("pune");
		list1.add("beed");
		FavModel fav1 = new FavModel("u1", list1);
		list1.add("mumbai");
		when(favService.addCity("u1", "mumbai")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fav/u1/mumbai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}
	
	@Test
	public void testAddCityFailure() throws Exception {
		
		list1.add("pune");
		list1.add("beed");
		FavModel fav1 = new FavModel("u1", list1);
		list1.add("mumbai");
		when(favService.addCity("u1", "mumbai")).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fav/u1/mumbai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isConflict());
		
	}
	
	
	@Test
	public void testDeleteCitySuccess() throws Exception {
		list1.add("pune");
		list1.add("beed");
		FavModel fav1 = new FavModel("u1", list1);
		list1.remove(1);
		when(favService.deleteCity("u1", "beed")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fav/u1/beed").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
	
	}

	@Test
	public void testDeleteCityFailure() throws Exception {
		list1.add("pune");
		list1.add("beed");
		FavModel fav1 = new FavModel("u1", list1);
		list1.remove(1);
		when(favService.deleteCity("u1", "mumbai")).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fav/u1/mumbai").contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	
	}
}
