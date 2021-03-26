package com.stackroute.subscriptionservice.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Subscription {

	@Id
	private String subscriptionId;
	String subscriptionName;
	String subscriptionCreatedBy;
	Date subscriptionCreatedDate;
	String subscriptionPrice;
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getSubscriptionName() {
		return subscriptionName;
	}
	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}
	public String getSubscriptionCreatedBy() {
		return subscriptionCreatedBy;
	}
	public void setSubscriptionCreatedBy(String subscriptionCreatedBy) {
		this.subscriptionCreatedBy = subscriptionCreatedBy;
	}
	public Date getSubscriptionCreatedDate() {
		return subscriptionCreatedDate;
	}
	public void setSubscriptionCreatedDate(Date subscriptionCreatedDate) {
		this.subscriptionCreatedDate = subscriptionCreatedDate;
	}
	public String getSubscriptionPrice() {
		return subscriptionPrice;
	}
	public void setSubscriptionPrice(String subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}
	
	
}
