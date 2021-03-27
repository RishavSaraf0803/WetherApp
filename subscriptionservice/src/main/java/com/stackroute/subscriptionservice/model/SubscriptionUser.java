package com.stackroute.subscriptionservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SubscriptionUser {

	@Id
	String userId;
	List<String> subsList;
	
	
	
	public SubscriptionUser() {
		super();
	}
	public SubscriptionUser(String userId, List<String> subsList) {
		super();
		this.userId = userId;
		this.subsList = subsList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getSubsList() {
		return subsList;
	}
	public void setSubsList(List<String> subsList) {
		this.subsList = subsList;
	}
	
	
}
