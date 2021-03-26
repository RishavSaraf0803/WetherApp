package com.stackroute.subscriptionservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.Subscription;
import com.stackroute.subscriptionservice.service.SubscriptionService;


@RestController
@RequestMapping("/api/subscirption")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/")
	public String index() {
		return "Subscription";
	}
	//Create new Subscription
	@PostMapping
	 public ResponseEntity<?> createSubscription(@RequestBody Subscription subscription){
		
		boolean status = subscriptionService.createSubcription(subscription);
		if(status) {
			return new ResponseEntity<Subscription>(subscription,HttpStatus.CREATED);
		}
		
		else {
			return new ResponseEntity<String>("Subcription not created",HttpStatus.CONFLICT);
		}
		
	}
	//Get All SubcriptionData
	@GetMapping
	public ResponseEntity<?> getAllSubcription(){
		List<Subscription> subscriptionList = subscriptionService.getAllSubscription();
		return new ResponseEntity<List<Subscription>>(subscriptionList,HttpStatus.OK);
	}
	
	//GetSubcriptionData By UserId
	@GetMapping("/{subscriptionId}")
	public ResponseEntity<?> getSubcriptionByUerId(@PathVariable("subscriptionId") String subscriptionId){
		
		try {
		Subscription subscription = subscriptionService.getSubscriptionBySubscriptionId(subscriptionId);
		
		return new ResponseEntity<Subscription>(subscription ,HttpStatus.OK);
		}
		catch(SubscriptionsNotFoundException e) {
			
			return new ResponseEntity<String>("User not subscribed",HttpStatus.NOT_FOUND);
	}
		
	}
	//Update Subscription
	@PutMapping
	public ResponseEntity<?> updateSubscription(@RequestBody Subscription subscription){
		try {
			Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
			return new ResponseEntity<Subscription>(updatedSubscription,HttpStatus.OK);
		}
		catch(SubscriptionsNotFoundException e) {
			return new ResponseEntity<String>("Subscription not found",HttpStatus.NOT_FOUND);
		}
	}
	//Delete Subscription
	@DeleteMapping("/{subscriptionId}")
	public ResponseEntity<?> removeSubscription(@PathVariable("subscriptionId") String subscriptionId){
		try { 
			   if(subscriptionService.removeSubscription(subscriptionId)) {
				   
				   return new ResponseEntity<String>("Subscription deleted",HttpStatus.OK);
			   }
			}
			catch(Exception e) {
				
				return new ResponseEntity<String>("Subscription not deleted",HttpStatus.NOT_FOUND);
		}
			
			return new ResponseEntity<String>("Subscription not deleted",HttpStatus.NOT_FOUND);
		}



}
	

