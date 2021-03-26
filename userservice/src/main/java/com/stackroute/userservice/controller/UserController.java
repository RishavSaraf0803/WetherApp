package com.stackroute.userservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.services.UserServices;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

	private UserServices userServices;

	@Autowired
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}
	
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
		
		try {
			userServices.saveUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> loginUser(@RequestBody UserModel user) {
		
		Map<String, String> map = new HashMap<>();
		try {
			user.setUserAddedDate(new Date());
			if(userServices.findByUserIdAndPassword(user.getUserId(), user.getUserPassword()) != null) {
				String jwtToken = getToken(user.getUserId(), user.getUserPassword());
				map.put("token", jwtToken);
				map.put("message", "user logged in successfully");
				return new ResponseEntity<>(map,HttpStatus.OK);
			}
			
		}catch(Exception e) {
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
	}


// Generate JWT token
	public String getToken(String username, String password) throws Exception {
			
		Claims claims = Jwts.claims();
		claims.put("username", username);
		claims.put("password", password);
		return Jwts.builder()
				.setSubject("weatherapp")
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis()+300000))
				.signWith(SignatureAlgorithm.HS256, "weatherapp")
				.compact();        
        
	}

	@PostMapping("/api/v1/user")
	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
	public ResponseEntity<?> registerUser(@RequestBody UserModel user, HttpServletRequest request) {
		
		try {
			this.userServices.registerUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/api/v1/user/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
	public ResponseEntity<?> updateUser(@PathVariable("id") String userId, @RequestBody UserModel user) {
		
		try {
			if (userServices.updateUser(userId, user) != null) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/v1/user/{id}")
	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String userId) {
		
		try {
			if (userServices.deleteUser(userId)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/v1/user/{id}")
	//@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "string", paramType = "header")
	public ResponseEntity<?> getUser(@PathVariable("id") String userId) {
		
		try {
			if (userServices.getUserById(userId) != null) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

