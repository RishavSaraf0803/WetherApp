package com.stackroute.userservice.services;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.UserModel;

public interface UserServices {
	
	 UserModel registerUser(UserModel user) throws UserAlreadyExistsException;
	 UserModel updateUser(String userId, UserModel user) throws UserNotFoundException;
	 boolean deleteUser(String userId) throws UserNotFoundException;
	 UserModel getUserById(String userId) throws UserNotFoundException;
	 UserModel findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	 boolean saveUser(UserModel user) throws UserAlreadyExistsException;
}
