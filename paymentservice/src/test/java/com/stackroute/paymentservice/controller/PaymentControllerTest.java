package com.stackroute.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.paymentservice.model.PaymentServiceModel;
import com.stackroute.paymentservice.services.PaymentServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private PaymentServices paymentServices;
    @InjectMocks
    private PaymentServiceController paymentController;
   
  
    
    private PaymentServiceModel payment;
    

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
        payment = new PaymentServiceModel();
        payment.setCardNo("31415926");
        payment.setCvv("202");
        payment.setExpDate("09/22");
       
    }


    @Test
    public void testAddPayment() throws Exception {
    	
        Mockito.when(paymentServices.addPayment(payment)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payment").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(payment)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void validatePayment() throws Exception {
    	
        Mockito.when(paymentServices.addPayment(payment)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payment/validate").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(payment)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    
    private static String jsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
