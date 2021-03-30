package com.stackroute.userservice.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.UserModel;

//@Repository
//public interface UserRepository extends JpaRepository<UserModel, Integer> {
//	UserModel findByUserNameAndUserPassword(String userName, String userPassword);
//}

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
