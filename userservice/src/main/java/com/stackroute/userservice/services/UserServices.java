package com.stackroute.userservice.services;

import java.util.Optional;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserIdAndPasswordMismatchException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.UserModel;

public interface UserServices {
	
	 UserModel registerUser(UserModel user) throws UserAlreadyExistsException;
	 Optional<UserModel> getUserById(String userId) throws UserNotFoundException;
	 UserModel findByUserNameAndPassword(String userName, String password) throws UserNotFoundException, UserIdAndPasswordMismatchException;
	 boolean saveUser(UserModel user) throws UserAlreadyExistsException;
	 //UserModel updateUser(String userId, UserModel user) throws UserNotFoundException;
	 //boolean deleteUser(String userId) throws UserNotFoundException;
}
