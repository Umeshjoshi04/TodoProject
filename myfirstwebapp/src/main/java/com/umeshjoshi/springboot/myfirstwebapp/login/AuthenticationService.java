package com.umeshjoshi.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("12");
		boolean isValidPassword = password.equalsIgnoreCase("12");
		
		return isValidUserName && isValidPassword;
	}
}