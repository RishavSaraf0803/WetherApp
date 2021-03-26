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

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
public class UserController {

	/*
	 * Autowiring should be implemented for the UserService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */

	private UserServices userServices;

	@Autowired
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}
	
	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the user created successfully. 
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user
	 * 
	 * This handler method should map to the URL "/api/v1/auth/register" using HTTP POST method
	 */

	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
		
		try {
			userServices.saveUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	/* Define a handler method which will authenticate a user by reading the Serialized user
	 * object from request body containing the username and password. The username and password should be validated 
	 * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries. 
	 * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
	 * token will be generated. The token should be returned back to the caller along with the API response.
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 200(OK) - If login is successful
	 * 2. 401(UNAUTHORIZED) - If login is not successful
	 * 
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP POST method
	*/

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

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the user created successfully. 
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user
	 * 
	 * This handler method should map to the URL "/user" using HTTP POST method
	 */

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
	
	/*
	 * Define a handler method which will update a specific user by reading the
	 * Serialized object from request body and save the updated user details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the user updated successfully.
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP PUT method.
	 */
	
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


	/*
	 * Define a handler method which will delete a user from a database.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the user deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid userId without {}
	 */
	
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

	/*
	 * Define a handler method which will show details of a specific user. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the user found successfully. 
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found. 
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP GET method where "id" should be
	 * replaced by a valid userId without {}
	 */
	
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

