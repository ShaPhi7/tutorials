package com.baeldung.crypto.exception;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.util.encoders.Base64Encoder;

import com.baeldung.crypto.utils.CryptoUtils;
import com.google.common.primitives.Bytes;

public class ZZPadding {

	//SecretKey key = CryptoUtils.generateKey();
	
	public static void broken(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
	{
		//so this works fine when you encrypt and decrypt as it's supposed to but you can break it by not having padding there.
		//Because we start with 20 bytes but it gets encrypted into 32 bytes as we add on padding etc.
		Cipher cipher = Cipher.getInstance("AES");
		
		String plainText = "12345678901234567890";
		
		IvParameterSpec iv = CryptoUtils.getIV();
        byte[] nonce = new byte[17];
        new SecureRandom().nextBytes(nonce);
        iv = new IvParameterSpec(nonce);
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] plainTextBytes = plainText.getBytes();
		byte[] cipherTextBytes = cipher.doFinal(plainTextBytes);
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] doFinal = cipher.doFinal(plainTextBytes);
		String string = doFinal.toString();
		System.out.println(new String(doFinal, StandardCharsets.UTF_8));
	}
	
	/**
	 * works
	 * @param key
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static void works(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
	{
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		String plainText = "12345678901234567890";
		
		IvParameterSpec iv = CryptoUtils.getIV();
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);
        iv = new IvParameterSpec(nonce);
		
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] bytes = plainText.getBytes();
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		
		byte[] doFinal = cipher.doFinal(cipherTextBytes);
		String string = doFinal.toString();
		System.out.println(new String(doFinal, StandardCharsets.UTF_8));
	}
	
	public static void fixed(SecretKey key) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
	{
		Cipher cipher = Cipher.getInstance("AES");
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		String cipherText = "1234567890123456";
		cipher.doFinal(cipherText.getBytes());
	}
	

	public static void useStreamCipher(SecretKey key, IvParameterSpec iv) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
	{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		
		String cipherText = "12345678901234567890";
		cipher.doFinal(cipherText.getBytes());
	}
	
	public static void usePadding(SecretKey key, IvParameterSpec iv) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		
		String cipherText = "12345678901234567890";
		System.out.println(cipherText.getBytes().length);
		cipher.doFinal(cipherText.getBytes());
		
		
	}
	
}
