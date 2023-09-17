package com.ibm.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordManagerUtil {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public String encodePassword(String plainText) {
		return passwordEncoder.encode(plainText);
	}
	
	public boolean matches(String plainText, String encodedPassowrd) {
		return passwordEncoder.matches(plainText, encodedPassowrd);
	}

}
