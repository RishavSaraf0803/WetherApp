package com.stackroute.favoriteservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class FavModel {

	@Id
	private String userId;
	private List<String> list;
	
	
	public FavModel() {
		
	}
	
	public FavModel(String userId, List<String> list) {
		super();
		this.userId = userId;
		this.list = list;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "FavModel [userId=" + userId + ", list=" + list + "]";
	}

}
