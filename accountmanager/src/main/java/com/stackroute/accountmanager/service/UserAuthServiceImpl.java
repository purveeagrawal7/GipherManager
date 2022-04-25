package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.repository.UserAuthRepository;
import com.stackroute.accountmanager.util.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.util.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {
	
	private UserAuthRepository userAuthRepo;
	
	@Autowired
	public UserAuthServiceImpl(UserAuthRepository userAuthRepo)
	{
		this.userAuthRepo = userAuthRepo;
	}
	
    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
    	System.out.println("got: "+ userId + " "+ password);
        User user = null;
        User validUser = userAuthRepo.findByUserIdAndPassword(userId, password);
        if(validUser == null)
        {
        	throw new UserNotFoundException("User not found!!");
        }
        else {
        	user = validUser;
        }
        System.out.println("SUCCESSFUL END");
    	return user;
    }

	/*
	 * This method should be used to save a new User.
	 */
    
//    @Override
//    public User register(User user) throws UserAlreadyExistsException {
//    Optional<User> existingUser = userAuthRepo.findById(null);
//
//    if(existingUser.isPresent())
//    throw new UserAlreadyExistsException("Given user is already present with same id");
//
//    else
//    {
//    userAuthRepo.save(user);
//    return user;
//    }
//    }
    
    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
    	System.out.println("in impl save user");
    	System.out.println(user.getFirstName());
    	System.err.println(user.getLastName());
        boolean createdUser = false;
//        User existingUser = userAuthRepo.getOne(user.getUserId());
        Optional<User> existingUser = userAuthRepo.findById(user.getUserId());
        if(existingUser.isPresent())
        {
        	throw new UserAlreadyExistsException("User already exists!!");
        }
        else {
        	userAuthRepo.save(user);
        	createdUser = true;
        }
    	return createdUser;
    }
}
