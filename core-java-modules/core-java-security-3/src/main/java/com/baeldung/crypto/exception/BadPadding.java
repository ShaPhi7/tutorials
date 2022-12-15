package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import com.baeldung.crypto.utils.CryptoUtils;

public class BadPadding {

	public static void usingWrongKeys() throws InvalidKeyException, GeneralSecurityException
	{
		SecretKey encryptionKey = CryptoUtils.getFixedKey();
		SecretKey differentKey = CryptoUtils.getKeyForText("ThisGivesUsAnAlternative");
		
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher.init(Cipher.DECRYPT_MODE, differentKey);
		
		cipher.doFinal(cipherTextBytes);
	}
	
	public static void usingWrongAlgorithm() throws InvalidKeyException, GeneralSecurityException
	{
		SecretKey encryptionKey = CryptoUtils.getFixedKey();
	
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, encryptionKey);
		
		cipher.doFinal(cipherTextBytes);
	}
	
	public static void usingWrongPadding() throws InvalidKeyException, GeneralSecurityException
	{
		SecretKey encryptionKey = CryptoUtils.getFixedKey();
		
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, encryptionKey);

		cipher.doFinal(cipherTextBytes);
	}
}
