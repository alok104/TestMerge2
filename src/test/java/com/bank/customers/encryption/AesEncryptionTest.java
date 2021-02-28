package com.bank.customers.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AesEncryptionTest {
	
	
	@Test
	public void testEncryption() {
		final String secretKey = "ssshhhhhhhhhhh!!!!";
	     
	    String originalString = "testencryption";
	    String encryptedString = AesEncryption.encrypt(originalString, secretKey) ;
	    String decryptedString = AesEncryption.decrypt(encryptedString, secretKey) ;
	    assertEquals(originalString, decryptedString);
	}
	
	@Test
	public void tesNullKey() {
		final String secretKey = null;
	     
	    String originalString = "testencryption";
	    String encryptedString = AesEncryption.encrypt(originalString, secretKey) ;
	    String decryptedString = AesEncryption.decrypt(encryptedString, secretKey) ;
	    assertEquals(originalString, decryptedString);
	}
	
	@Test
	public void testNullValue() {
		final String secretKey = "ssshhhhhhhhhhh!!!!";
	     
	    String originalString = null;
	    String encryptedString = AesEncryption.encrypt(originalString, secretKey) ;
	    String decryptedString = AesEncryption.decrypt(encryptedString, secretKey) ;
	    assertEquals(originalString, decryptedString);
	}
	
	@Test
	public void testBlankValue() {
		final String secretKey = " ";
	     
	    String originalString = null;
	    String encryptedString = AesEncryption.encrypt(originalString, secretKey) ;
	    String decryptedString = AesEncryption.decrypt(encryptedString, secretKey) ;
	    assertEquals(originalString, decryptedString);
	}
}
