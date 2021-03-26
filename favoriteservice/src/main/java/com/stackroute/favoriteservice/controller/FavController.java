package com.stackroute.favoriteservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favoriteservice.service.FavService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/fav")
public class FavController {

	
	
	@Autowired
	private FavService service;
	
	public FavController(FavService service) {
		this.service = service;
	}
	
	
	@PostMapping("/{userId}/{city}")
	public ResponseEntity<?> addCity(@PathVariable String userId, @PathVariable String city) {
		boolean status = service.addCity(userId, city);
		if(status)
			return new ResponseEntity<String>("Successfully added city",HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Couldn't add city",HttpStatus.CONFLICT);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllCities(@PathVariable String userId) {
		List<String> list = service.getAllCities(userId);
		if(!list.isEmpty())
			return new ResponseEntity<>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>("You have not added any city", HttpStatus.NOT_FOUND);
			
	}

	@DeleteMapping("/{userId}/{city}")
	public ResponseEntity<?> deleteCity(@PathVariable String userId, @PathVariable String city) {
		boolean flag = service.deleteCity(userId, city);
		if(flag) {
			return new ResponseEntity<String>("Successfully deleted city",HttpStatus.OK);
		}
		return new ResponseEntity<>("City not found",HttpStatus.NOT_FOUND);
		
	}
	
	
}
