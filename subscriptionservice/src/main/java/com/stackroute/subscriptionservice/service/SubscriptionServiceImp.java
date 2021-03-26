package com.stackroute.subscriptionservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.Subscription;
import com.stackroute.subscriptionservice.repository.SubscriptionRepo;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

	 @Autowired
	 private SubscriptionRepo subscriptionRepo;
	 
	 Subscription subscription = null;
	 List<Subscription> subscriptionList = null;
	 SubscriptionServiceImp(SubscriptionRepo subscriptionRepo){
		 this.subscriptionRepo = subscriptionRepo;
	 }
	 //Create new Subscription
	 public boolean createSubcription(Subscription subscription) {
		 
		 if(subscriptionRepo.existsById(subscription.getSubscriptionId())) {
			 return false;
		 }
		 else {
			 
			 subscription.setSubscriptionCreatedDate(new Date());
			 if(subscriptionRepo.save(subscription) != null) {
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
	 }
	 
	 //getAllSubscription
	 
	 public List<Subscription> getAllSubscription(){
		 
		 subscriptionList = subscriptionRepo.findAll();
		 return subscriptionList;
	 }
	 
	 //getSubscriptionDataBySubscriptionId
	 public Subscription getSubscriptionBySubscriptionId(String subscriptionId) throws SubscriptionsNotFoundException{
		try {
			
				Subscription subscription =  subscriptionRepo.findById(subscriptionId).get();
			return subscription;
		}
		catch(NoSuchElementException e) {
			throw new SubscriptionsNotFoundException("Subscription not found");
		}
	 }
	 //Delete Subscription
	 public boolean removeSubscription(String subscriptionId) throws SubscriptionsNotFoundException{
		 try {
			Subscription subscription = subscriptionRepo.findById(subscriptionId).get();
			subscriptionRepo.delete(subscription);
			return true;
		 }
		 catch(NoSuchElementException e) {
			 throw new SubscriptionsNotFoundException("SubsriptionDoesNotexist");
		 }
	 }
	 
	 //Update Subscription
	 public Subscription updateSubscription(Subscription subscription) throws SubscriptionsNotFoundException{
		 try {
			 Subscription fetchSubscription = subscriptionRepo.findById(subscription.getSubscriptionId()).get();
			 fetchSubscription.setSubscriptionName(subscription.getSubscriptionName());
			 fetchSubscription.setSubscriptionCreatedDate(new Date());
			 fetchSubscription.setSubscriptionPrice(subscription.getSubscriptionPrice());
			 
			 subscriptionRepo.save(fetchSubscription);
			 return fetchSubscription;
		 }
		 catch(NoSuchElementException e) {
			 throw new SubscriptionsNotFoundException("Subscription does not exist");
		 }
	 }

	 
	 
	 
}
