package com.stackroute.subscriptionservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Feature {

	@Id
	String featureName;
	String featureSubscribeBy;
	Date featureSubscribeDate;
	String featurePrice;
	
	
	public Feature() {
		super();
	}
	public Feature(String featureName, String featureSubscribeBy, Date featureSubscribeDate, String featurePrice) {
		
		this.featureName = featureName;
		this.featureSubscribeBy = featureSubscribeBy;
		this.featureSubscribeDate = featureSubscribeDate;
		this.featurePrice = featurePrice;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getFeatureSubscribeBy() {
		return featureSubscribeBy;
	}
	public void setFeatureSubscribeBy(String featureSubscribeBy) {
		this.featureSubscribeBy = featureSubscribeBy;
	}
	public Date getFeatureSubscribeDate() {
		return featureSubscribeDate;
	}
	public void setFeatureSubscribeDate(Date featureSubscribeDate) {
		this.featureSubscribeDate = featureSubscribeDate;
	}
	public String getFeaturePrice() {
		return featurePrice;
	}
	public void setFeaturePrice(String featurePrice) {
		this.featurePrice = featurePrice;
	}
	
	
}
