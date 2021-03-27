package com.stackroute.userservice.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
	UserModel findByUserNameAndUserPassword(String userName, String userPassword);
}
