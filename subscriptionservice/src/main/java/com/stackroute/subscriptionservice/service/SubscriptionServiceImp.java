package com.stackroute.subscriptionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.SubscriptionUser;
import com.stackroute.subscriptionservice.repository.SubscriptionRepo;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

	 @Autowired
	 private SubscriptionRepo subscriptionRepo;
	 
	 SubscriptionUser subscriptionUser = null;

	 SubscriptionServiceImp(SubscriptionRepo subscriptionRepo){
		 this.subscriptionRepo = subscriptionRepo;
	 }
	 //Create new Subscription
	 public boolean subscribe(String userId,String feature) {
		 
		 if(subscriptionRepo.existsById(userId)) {
			 subscriptionUser = subscriptionRepo.findById(userId).get();
			 List<String> subscriptions = subscriptionUser.getSubsList();
			 if(subscriptions.contains(feature)) {return false;}
			 subscriptions.add(feature);
			 subscriptionUser.setSubsList(subscriptions);
			 subscriptionRepo.save(subscriptionUser);
			 return true;
		 }
		 else {
			 subscriptionUser = new SubscriptionUser();
			 subscriptionUser.setUserId(userId);
			 List<String> list = new ArrayList<>();
			 list.add(feature);
			 subscriptionUser.setSubsList(list);
			 subscriptionRepo.insert(subscriptionUser);
			 return true;
		 }
		 

		}

	
	 
	 //getAllSubscription
	 
	 public List<String> getAllSubscription(String userId) throws SubscriptionsNotFoundException{
		 
		 try {
			 return subscriptionRepo.findById(userId).get().getSubsList();
		 }
		 catch(NoSuchElementException e) {
			 throw new SubscriptionsNotFoundException("No subscription exist");
		 }
	 }
	 
	 //getSubscriptionDataBySubscriptionId
	 
	 //Delete Subscription
	 public boolean unsubscribe(String userId, String feature) throws SubscriptionsNotFoundException{
		 try {
			 subscriptionUser = subscriptionRepo.findById(userId).get();
			 List<String> list = subscriptionUser.getSubsList();
			 for(String feat: list) {
				 if(feat.equals(feature)) {
					 list.remove(feat);
					 subscriptionUser.setSubsList(list);
					 subscriptionRepo.save(subscriptionUser);
					 return true;
				 }
			 }
			 return false;
			 
		 }
		 catch(NoSuchElementException e) {
			 throw new SubscriptionsNotFoundException("SubsriptionDoesNotexist");
		 }
	 }
	 
	 

	 
	 
	 
}
