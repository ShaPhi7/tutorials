package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.baeldung.crypto.utils.CryptoUtils;

public class InvalidKey {
	
	public static void decryptUsingCBCWithNoIV() throws InvalidKeyException, GeneralSecurityException
	{
		//TODO - move to it's own encryption method
		SecretKey encryptionKey = CryptoUtils.getFixedKey();
	
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, encryptionKey);
		
		cipher.doFinal(cipherTextBytes);
	}
	
	public static void decryptUsingCBCWithIV() throws InvalidKeyException, GeneralSecurityException
	{
		//TODO - move to it's own encryption method
		SecretKey encryptionKey = CryptoUtils.getFixedKey();
		byte[] ivBytes = new byte[]{'B', 'a', 'e', 'l', 'd', 'u', 'n', 'g', 'I', 's', 'G', 'r', 'e', 'a', 't', '!'};
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
	
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, ivParameterSpec);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, encryptionKey, ivParameterSpec);
		
		byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);
		
	}
	
	public static void encryptWithKeyTooShort() throws InvalidKeyException, GeneralSecurityException
	{
		//TODO - move to it's own encryption method
		SecretKey encryptionKey = CryptoUtils.getKeyForText("ThisIsTooShort");
	
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
	}
	
	public static void encryptWithKeyTooLong() throws InvalidKeyException, GeneralSecurityException
	{
		//TODO - move to it's own encryption method
		SecretKey encryptionKey = CryptoUtils.getKeyForText("ThisTextIsTooLong");
	
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
	}
	
}
