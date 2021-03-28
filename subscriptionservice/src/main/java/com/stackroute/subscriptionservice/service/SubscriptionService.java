package com.stackroute.subscriptionservice.service;

import java.util.List;

import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;
import com.stackroute.subscriptionservice.model.Feature;


public interface SubscriptionService {
//
//	boolean createSubcription(Subscription subcription);
//	Subscription getSubscriptionBySubscriptionId(String subscriptionId)throws SubscriptionsNotFoundException;
//	List<Subscription> getAllSubscription();
//	boolean removeSubscription(String subscriptionId)throws SubscriptionsNotFoundException;
//	Subscription updateSubscription(Subscription subcription) throws SubscriptionsNotFoundException;

	boolean subscribe(Feature feature);
	boolean unsubscribe(Feature feature) throws SubscriptionsNotFoundException;
	List<Feature> getAllSubscription(String userId) throws SubscriptionsNotFoundException;
	
}
