package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baeldung.crypto.utils.CryptoUtils;

public class ZZExceptionUnitTest {

	private SecretKey key;
	private IvParameterSpec iv;
	private String cipherText;
	
	@Before
	public void generateKey() throws GeneralSecurityException
	{
		key = CryptoUtils.generateKey();
		 byte[] nonce = new byte[16];
	     new SecureRandom().nextBytes(nonce);
	     iv = new IvParameterSpec(nonce);
	     
	     String input = "abcdefghijklmnopqrstuvwxyz";
	     
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        byte[] cipherTextBytes = cipher.doFinal(input.getBytes());
	        cipherText = Base64.getEncoder().encodeToString(cipherTextBytes);
	}
	
	@Test
	public void givenAesEncryptionThenThrowsException()
	{
		Assert.assertThrows(IllegalBlockSizeException.class, () -> ZZPadding.broken(key));
	}
	
	@Test
	public void fixed()
	{
		try {
			ZZPadding.fixed(key);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void streamCipher()
	{
		try {
			ZZPadding.useStreamCipher(key, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void padding()
	{
		try {
			ZZPadding.usePadding(key, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void givenAesEncryptionThenThrowsException()
	{
		Assert.assertThrows(IllegalBlockSizeException.class, () -> Padding.broken(key));
	}*/
}
