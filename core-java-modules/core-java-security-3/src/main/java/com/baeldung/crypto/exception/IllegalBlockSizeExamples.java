package com.baeldung.crypto.exception;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class IllegalBlockSizeExamples {

	public static byte[] encryptWithoutPadding(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		String sampleText = "12345678901234567890";
		byte[] plainTextBytes = sampleText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(plainTextBytes);
	}
	
	public static byte[] decryptTextThatIsNotEncrypted(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		//note that this text is not encrypted at any point in this method.
		String sampleText = "12345678901234567890";
		byte[] unencryptedCipherTextBytes = sampleText.getBytes();
		
		return decryptWithPadding(key, unencryptedCipherTextBytes);
	}

	public static byte[] encryptWithPadding(SecretKey key, byte[] bytes) throws NoSuchAlgorithmException, NoSuchPaddingException,
	InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		return cipherTextBytes;
	}
	
	public static byte[] decryptWithPadding(SecretKey key, byte[] cipherTextBytes) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(cipherTextBytes);
	}
}
