package com.stackroute.favoriteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favoriteservice.model.FavModel;

@Repository
public interface FavRepository  extends MongoRepository<FavModel, String> {

}
