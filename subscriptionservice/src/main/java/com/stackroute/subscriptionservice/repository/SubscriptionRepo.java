package com.stackroute.subscriptionservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.subscriptionservice.model.Subscription;

@Repository
public interface SubscriptionRepo extends MongoRepository<Subscription,String>{

}
