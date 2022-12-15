package com.baeldung.crypto.exception;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class IllegalBlockSize {

	public static byte[] verySimpleDecrpytion(byte[] cipherTextBytes, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(cipherTextBytes);
	}
	
	public static byte[] verySimpleEncryptionWithoutPadding(byte[] plainTextBytes, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(plainTextBytes);
	}
	
}
