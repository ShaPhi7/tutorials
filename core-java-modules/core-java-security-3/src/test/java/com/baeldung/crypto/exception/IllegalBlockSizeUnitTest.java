package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baeldung.crypto.utils.CryptoUtils;

public class IllegalBlockSizeUnitTest {

	private SecretKey key;
	
	@Before
	public void generateKey() throws GeneralSecurityException
	{
		key = CryptoUtils.getFixedKey();
	}
	
	@Test
	public void whenEncryptingPlainTextWithoutPadding_thenIllegalBlockSizeExceptionIsThrown() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		String plainText = "12345678901234567890";
		byte[] plainTextBytes = plainText.getBytes();
		
		Assert.assertThrows(IllegalBlockSizeException.class, () -> IllegalBlockSize.verySimpleEncryptionWithoutPadding(plainTextBytes, key));
	}
	
	@Test
	public void givenCipherTextThatWasNotEncrypted_whenDecrypting_thenIllegalBlockSizeExceptionIsThrown() throws GeneralSecurityException
	{
		String cipherText = "12345678901234567890";
		byte[] cipherTextBytes = cipherText.getBytes();
		
		Assert.assertThrows(IllegalBlockSizeException.class, () -> IllegalBlockSize.verySimpleDecrpytion(cipherTextBytes, key));
	}
	
	@Test
	public void givenEncryptedPlainText_whenDecrypting_thenReturnsPlainText() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
		
		byte[] decryptedBytes = IllegalBlockSize.verySimpleDecrpytion(cipherTextBytes, key);
		String decryptedText = new String(decryptedBytes);
		Assert.assertEquals(plainText, decryptedText);
	}
}
