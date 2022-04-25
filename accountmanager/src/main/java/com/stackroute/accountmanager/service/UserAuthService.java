package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.util.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.util.exception.UserNotFoundException;

public interface UserAuthService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
    
//    public User register(User user) throws UserAlreadyExistsException;

}
