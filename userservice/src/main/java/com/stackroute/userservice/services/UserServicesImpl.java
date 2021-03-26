package com.stackroute.userservice.services;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.repositroy.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{
	
	private final UserRepository userRepository;

	public UserServicesImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserModel registerUser(UserModel user) throws UserAlreadyExistsException {

		UserModel newUser = userRepository.save(user);
		
		if(newUser != null)
			return newUser;
		throw new UserAlreadyExistsException("User already exists!");
	}
	
	public UserModel updateUser(String userId,UserModel user) throws UserNotFoundException {

		try {
			userRepository.save(user);
			return userRepository.findById(userId).get();
		}
		catch(Exception e) {
			throw new UserNotFoundException("User not found");
		}
	}

	public boolean deleteUser(String userId) throws UserNotFoundException {
		
//		try {
//			userRepository.deleteById(userId);
//			return true;
//		} 
//		catch (Exception e) {
//			throw new UserNotFoundException("UserNotFoundException");
//		}
		
		UserModel user = userRepository.findById(userId).get();
		if(user == null) throw new UserNotFoundException("UserNotFoundExceptoin");
		userRepository.delete(user);
		return true;
	}
	
	public UserModel getUserById(String userId) throws UserNotFoundException {
		
		try {
			UserModel user = userRepository.findById(userId).get();
			if (user != null) {
				return user;
			} else {
				throw new UserNotFoundException("UserNotFoundException");
			}
		} catch (Exception e) {
			throw new UserNotFoundException("UserNotFoundException");
		}
	}
	
    @Override
    public UserModel findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

    	return userRepository.findByUserIdAndUserPassword(userId, password);
    }
    
    @Override
    public boolean saveUser(UserModel user) throws UserAlreadyExistsException {
       
    	try {
			Optional<UserModel> newUser = Optional.ofNullable(this.userRepository.findById(user.getUserId())).get();
			if (!newUser.isPresent()) {
				this.userRepository.save(user);
				return Boolean.TRUE;
			}else {
				throw new UserAlreadyExistsException("Cannot Register User");
			}
		} catch (Exception e) {
			throw new UserAlreadyExistsException(e.getMessage());
		}
    }
}
