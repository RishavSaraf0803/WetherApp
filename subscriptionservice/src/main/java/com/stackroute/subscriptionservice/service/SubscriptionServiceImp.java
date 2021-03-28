package com.stackroute.subscriptionservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.Feature;
import com.stackroute.subscriptionservice.model.SubscriptionUser;
import com.stackroute.subscriptionservice.repository.SubscriptionRepo;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

	 @Autowired
	 private SubscriptionRepo subscriptionRepo;
	 
	 List<Feature> featureList = null;
	 SubscriptionUser subscriptionUser = null;

	 SubscriptionServiceImp(SubscriptionRepo subscriptionRepo){
		 this.subscriptionRepo = subscriptionRepo;
	 }
	 //Create new Subscription
	 public boolean subscribe(Feature feature) {
		 
		 if(subscriptionRepo.existsById(feature.getFeatureSubscribeBy())) {
			 subscriptionUser = subscriptionRepo.findById(feature.getFeatureSubscribeBy()).get();
			 List<Feature> subscriptions = subscriptionUser.getSubsList();
			 for(Feature fet: subscriptions) {
				 if(fet.getFeatureName().equals(feature.getFeatureName()))return false;
			 }
			 feature.setFeatureSubscribeDate(new Date());
			 subscriptions.add(feature);
			 subscriptionUser.setSubsList(subscriptions);
			 subscriptionRepo.save(subscriptionUser);
			 return true;
		 }
		 else {
			 subscriptionUser = new SubscriptionUser();
			 subscriptionUser.setUserId(feature.getFeatureSubscribeBy());
			 List<Feature> list = new ArrayList<>();
			 feature.setFeatureSubscribeDate(new Date());
			 list.add(feature);
			 subscriptionUser.setSubsList(list);
			 subscriptionRepo.insert(subscriptionUser);
			 return true;
		 }
		 

		}

	
	 
	 //getAllSubscription
	 
	 public List<Feature> getAllSubscription(String userId) throws SubscriptionsNotFoundException{
		 
		 try {
			 return subscriptionRepo.findById(userId).get().getSubsList();
		 }
		 catch(NoSuchElementException e) {
			 throw new SubscriptionsNotFoundException("No subscription exist");
		 }
	 }
	 
	 //getSubscriptionDataBySubscriptionId
	 
	 //Delete Subscription
	 public boolean unsubscribe(Feature feature) throws SubscriptionsNotFoundException{
		 try {
			 subscriptionUser = subscriptionRepo.findById(feature.getFeatureSubscribeBy()).get();
			 List<Feature> list = subscriptionUser.getSubsList();
			 for(Feature feat: list) {
				 if(feat.getFeatureName().equals(feature.getFeatureName())) {
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
