package com.stackroute.userservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "UserModel")
public class UserModel {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	
	@NonNull
	private String userName;
	private String userPassword;
	//private String userImage;
	private Date userAddedDate;
	
	public UserModel() {
		
	}
	
	public UserModel(String userId, String userName, String userPassword, Date userAddedDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		//this.userImage = userImage;
		this.userAddedDate = userAddedDate;
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
//	public String getUserImage() {
//		return userImage;
//	}
//	public void setUserImage(String userImage) {
//		this.userImage = userImage;
//	}
	public Date getUserAddedDate() {
		return userAddedDate;
	}
	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}
	
}
