package com.stackroute.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.paymentservice.model.PaymentServiceModel;

@Repository
public interface PaymentServiceRepository extends MongoRepository<PaymentServiceModel,String>{
	

}
