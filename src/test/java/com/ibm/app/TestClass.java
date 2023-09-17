package com.ibm.app;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestClass {
	
	@Test
	void testOtp() {
		SecureRandom random = new SecureRandom();
		int otp = random.nextInt();
		System.out.println(otp);
		
	}
}
