package com.stackroute.userservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserModel {
	
	@Id
	private String userId;
	private String userName;
	private String userPassword;
	private String userImage;
	private Date userAddedDate;
	
	public UserModel() {
	}
	
	public UserModel(String string, String string1, String string2, String string3, Date date) {
		this.userId = string;
		this.userName =  string1;
		this.userPassword = string2;
		this.userImage = string3;
		this.userAddedDate = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public Date getUserAddedDate() {
		return userAddedDate;
	}
	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}
	
}
