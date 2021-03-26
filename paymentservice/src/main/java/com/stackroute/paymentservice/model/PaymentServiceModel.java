package com.stackroute.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PaymentServiceModel {
	@Id
	private String cardNo;
	private String cvv;
	private String expDate;
	
	public PaymentServiceModel() {
		
	}
	public PaymentServiceModel(String userName, String cardNo, String cvv, String expDate) {
		super();
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expDate = expDate;
	}


	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
}
