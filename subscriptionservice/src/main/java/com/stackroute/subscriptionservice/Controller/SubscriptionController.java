package com.stackroute.subscriptionservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.Feature;
import com.stackroute.subscriptionservice.service.SubscriptionService;


@RestController
@RequestMapping("/api/v1/sub")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	
	//Subscribe
	@PostMapping("/subscribe")
	 public ResponseEntity<?> createSubscription(@RequestBody Feature feature/*@PathVariable("userId") String userId,@PathVariable("feature") String feature*/){
		
		boolean status = subscriptionService.subscribe(feature);
		if(status) {
			return new ResponseEntity<Feature>(feature,HttpStatus.CREATED);
		}
		
		else {
			return new ResponseEntity<String>("Subscribtion Unsuccessfull",HttpStatus.CONFLICT);
		}
		
	}
	//Get All Subcriptions
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllSubcription(@PathVariable("userId") String userId){
		try {
		List<Feature> subscriptionList = subscriptionService.getAllSubscription(userId);
		if(subscriptionList.isEmpty()) {return new ResponseEntity<String>("No subscription available",HttpStatus.NOT_FOUND);}
		
		return new ResponseEntity<List<Feature>>(subscriptionList,HttpStatus.OK);
	}
		catch(SubscriptionsNotFoundException e) {
			return new ResponseEntity<String>("No subscription available",HttpStatus.NOT_FOUND);
		}
	}
	
		
	
	
	//Unsubscribe
	@DeleteMapping("/unsubscribe")
	public ResponseEntity<?> unsubscribe(@RequestBody Feature feature /*@PathVariable("userId") String userId , @PathVariable("feature") String feature*/){
		try { 
			   if(subscriptionService.unsubscribe(feature)) {
				   
				   return new ResponseEntity<String>("Subscription deleted",HttpStatus.OK);
			   }
			}
			catch(SubscriptionsNotFoundException e) {
				
				return new ResponseEntity<String>("Subscription not deleted",HttpStatus.NOT_FOUND);
		}
			
			return new ResponseEntity<String>("Subscription not deleted",HttpStatus.NOT_FOUND);
		}



}
	

