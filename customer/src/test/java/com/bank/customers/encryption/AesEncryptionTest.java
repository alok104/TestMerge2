package com.bank.customers.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bank.customers.exception.AesException;

public class AesEncryptionTest {
	
	
	@Test
	public void testEncryption() {
		final String secretKey = "secret";
	     
	    String originalString = "1000001";
	    String encryptedString = AesEncryption.encrypt(originalString, secretKey) ;
	    String decryptedString = AesEncryption.decrypt(encryptedString, secretKey) ;
	    assertEquals(originalString, decryptedString);
	}
	
	@Test
	public void tesNullKey() {
		final String secretKey = null;
	     
	    String originalString = "testencryption";
		Exception exception = assertThrows(AesException.class, () -> {
			AesEncryption.encrypt(originalString, secretKey) ;
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains("Error while encrypting"));
	}
	
	@Test
	public void testNullValue() {
		final String secretKey = "ssshhhhhhhhhhh!!!!";
	     
	    String originalString = null;
	    Exception exception = assertThrows(AesException.class, () -> {
			AesEncryption.encrypt(originalString, secretKey) ;
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains("Error while encrypting"));
	}
	
	@Test
	public void testBlankValue() {
		final String secretKey = " ";
	     
		 
	    String originalString = null;
	    Exception exception = assertThrows(AesException.class, () -> {
			AesEncryption.decrypt(originalString, secretKey) ;
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains("Error while decrypting"));
	}
}
