package com.stackroute.paymentservice.controller;

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

import com.stackroute.paymentservice.model.PaymentServiceModel;
import com.stackroute.paymentservice.services.PaymentServices;
import com.stackroute.paymentservice.controller.PaymentServiceController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

	@InjectMocks
	private PaymentServiceController paymentController;
	
	@Mock
	private PaymentServices paymentServices;
	
	
	@Autowired
	private MockMvc mockMvc;
	

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
	}
	
	@Test
	public void addPaymentTest() throws Exception {
		
		PaymentServiceModel payment = new PaymentServiceModel("314159265","202","02/22");
		when(paymentServices.addPayment(any())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(payment	)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}
	@Test
	public void validatePaymentSuccess() throws Exception {
		
		PaymentServiceModel payment1 = new PaymentServiceModel("314159265","202","02/22");
		when(paymentServices.getPaymentByCardNo("314159265")).thenReturn(payment1);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/payment/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(payment1)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
//	@Test
//	public void validatePaymentFailure() throws Exception {
//		
//		PaymentServiceModel payment2 = new PaymentServiceModel();
//		when(paymentServices.getPaymentByCardNo("123578")).thenReturn(payment2);
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/api/v1/payment/validate")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(payment2)))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}


}
