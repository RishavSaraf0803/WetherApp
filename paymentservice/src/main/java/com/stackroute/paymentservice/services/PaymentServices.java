package com.stackroute.paymentservice.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.paymentservice.exceptions.MethodNotFoundException;
import com.stackroute.paymentservice.model.PaymentServiceModel;
import com.stackroute.paymentservice.repository.PaymentServiceRepository;

@Service
public class PaymentServices {

	@Autowired
	private PaymentServiceRepository paymentRepository;
	
	public PaymentServiceModel getPaymentByCardNo(String cardNo) throws MethodNotFoundException{
		PaymentServiceModel payment;
		try {
		 payment = paymentRepository.findById(cardNo).get();
			
		}
		catch(NoSuchElementException e) {
	
			throw new MethodNotFoundException("Payment method invalid");
		}
		return payment;
	}
	public boolean addPayment(PaymentServiceModel payment) {
		
		PaymentServiceModel newPayment = paymentRepository.insert(payment);
		if(newPayment==null)
		{
			return false;
			
		}
		return true;
	}

	
}
