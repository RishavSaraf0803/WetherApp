package com.stackroute.subscriptionservice.repository;


import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.stackroute.subscriptionservice.model.Subscription;

@RunWith(SpringRunner.class) 
@DataMongoTest
public class SubscriptionRepoTest {

	@Autowired
	SubscriptionRepo subscriptionRepo;
	Subscription subscription;
	
	@Before
	public void setUp() throws Exception{
		subscription = new Subscription();
		subscription.setSubscriptionId("Jhon111");
		subscription.setSubscriptionName("Developer");
		subscription.setSubscriptionCreatedBy("Jhon111");
		subscription.setSubscriptionCreatedDate(new Date());
		subscription.setSubscriptionPrice("5000");
		
	}
	
    @After
    public void tearDown() throws Exception {

        subscriptionRepo.deleteAll();

    }
    
    @Test
    public void createSubscriptionTest() {

        subscriptionRepo.insert(subscription);
        Subscription fetchedsubscription = subscriptionRepo.findById("Jhon111").get();
        Assert.assertEquals(subscription.getSubscriptionId(), fetchedsubscription.getSubscriptionId());

    }
    
    
}
