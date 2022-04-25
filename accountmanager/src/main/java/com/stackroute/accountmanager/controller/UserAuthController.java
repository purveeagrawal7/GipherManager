package com.stackroute.accountmanager.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.service.UserAuthService;
import com.stackroute.accountmanager.util.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.util.exception.UserNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

	private UserAuthService userAuthService;
	
	@Autowired
    public UserAuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}

 
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		System.out.println("inside register user");
		System.out.println(user);
		try {
			boolean response = userAuthService.saveUser(user);
			System.out.println("checking response");
			System.out.println(response);
			return new ResponseEntity<Boolean>(response, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

		
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user)
	{
		try {
			User validUser =userAuthService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
			if(validUser == null)
			{
				return new ResponseEntity<String>("Invalid credentials!!",HttpStatus.UNAUTHORIZED);
			}
			else {
				String token = getToken(validUser);
				return new ResponseEntity<String>(token, HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	public String getToken(User user)
	{
		long expiryTime = 10_000_000;
		return Jwts.builder().setSubject(user.getUserId())
								.setIssuedAt(new Date(System.currentTimeMillis()))
								.setExpiration(new Date(System.currentTimeMillis()+expiryTime))
								.signWith(SignatureAlgorithm.HS256, "ibmfsd")

								.compact();
	}
    


}
