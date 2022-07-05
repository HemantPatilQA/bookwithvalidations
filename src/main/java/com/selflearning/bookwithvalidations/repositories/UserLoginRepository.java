package com.selflearning.bookwithvalidations.repositories;

import com.selflearning.bookwithvalidations.entities.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {
	
	UserLogin findByUsername(String username);
	
}