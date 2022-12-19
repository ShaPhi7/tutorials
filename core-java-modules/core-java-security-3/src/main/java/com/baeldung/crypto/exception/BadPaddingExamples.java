package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.baeldung.crypto.utils.CryptoUtils;

public class BadPaddingExamples {

	public static byte[] encryptAndDecryptUsingDifferentKeys() throws InvalidKeyException, GeneralSecurityException
	{
		SecretKey encryptionKey = CryptoUtils.getKeyForText("BaeldungIsASuperCoolSite");
		SecretKey differentKey = CryptoUtils.getKeyForText("ThisGivesUsAnAlternative");
		
		String plainText = "https://www.baeldung.com/";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher.init(Cipher.DECRYPT_MODE, differentKey);
		
		return cipher.doFinal(cipherTextBytes);
	}
	
	//TODO - add bytes into here and below. Do all the methods return byte[], or String?
	public static byte[] encryptAndDecryptUsingDifferentAlgorithms(SecretKey key) throws InvalidKeyException, GeneralSecurityException
	{
		String plainText = "https://www.baeldung.com/";
		byte[] bytes = plainText.getBytes();
		byte[] ivBytes = new byte[]{'B', 'a', 'e', 'l', 'd', 'u', 'n', 'g', 'I', 's', 'G', 'r', 'e', 'a', 't', '!'};
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(cipherTextBytes);
	}
	
	public static byte[] encryptAndDecryptUsingDifferentPaddings(SecretKey key) throws InvalidKeyException, GeneralSecurityException
	{
		String plainText = "https://www.baeldung.com/";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(cipherTextBytes);
	}
	
	public static byte[] encryptAndDecryptUsingSamePaddingKeyAndAlgorithm(SecretKey key, byte[] bytes) throws InvalidKeyException, GeneralSecurityException
	{		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(cipherTextBytes);
	}
}
