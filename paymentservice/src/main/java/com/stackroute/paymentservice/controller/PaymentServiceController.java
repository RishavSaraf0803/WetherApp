package com.stackroute.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.paymentservice.exceptions.MethodNotFoundException;
import com.stackroute.paymentservice.model.PaymentServiceModel;
import com.stackroute.paymentservice.services.PaymentServices;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentServiceController {

	@Autowired
	private PaymentServices paymentService;
	
	@PostMapping("/validate")
	public ResponseEntity<?> getPaymentMethod(@RequestBody PaymentServiceModel validate){
		PaymentServiceModel payment;
		try {
			payment = paymentService.getPaymentByCardNo(validate.getCardNo());
		}
		catch(MethodNotFoundException e) {
			return new ResponseEntity<String>("Payment Method is invalid",HttpStatus.NOT_FOUND);
		}
		
		if(payment.getCvv().equals(validate.getCvv()) && payment.getExpDate().equals(validate.getExpDate())) {
			
			return new ResponseEntity<String>("Payment Successfull",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Wrong payment details",HttpStatus.UNAUTHORIZED);
	
	}

	
	@PostMapping
	public ResponseEntity<?> addPaymentMethod(@RequestBody PaymentServiceModel payment){
		
		if(paymentService.addPayment(payment)) {
			
			return new ResponseEntity<PaymentServiceModel>(payment,HttpStatus.CREATED);
			
		}
		
		return new ResponseEntity<String>("Method not added",HttpStatus.CONFLICT);	
	}
}
