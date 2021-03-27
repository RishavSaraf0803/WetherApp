package com.stackroute.subscriptionservice.service;

import java.util.List;

import com.stackroute.subscriptionservice.exception.SubscriptionsNotFoundException;


public interface SubscriptionService {
//
//	boolean createSubcription(Subscription subcription);
//	Subscription getSubscriptionBySubscriptionId(String subscriptionId)throws SubscriptionsNotFoundException;
//	List<Subscription> getAllSubscription();
//	boolean removeSubscription(String subscriptionId)throws SubscriptionsNotFoundException;
//	Subscription updateSubscription(Subscription subcription) throws SubscriptionsNotFoundException;

	boolean subscribe(String userId,String feature);
	boolean unsubscribe(String userId, String feature) throws SubscriptionsNotFoundException;
	List<String> getAllSubscription(String userId) throws SubscriptionsNotFoundException;
	
}
