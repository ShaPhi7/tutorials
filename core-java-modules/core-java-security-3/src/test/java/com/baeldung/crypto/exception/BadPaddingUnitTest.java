package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baeldung.crypto.utils.CryptoUtils;

public class BadPaddingUnitTest {

	private SecretKey key;
	
	@Before
	public void before() throws GeneralSecurityException
	{
		key = CryptoUtils.getFixedKey();
	}
	
	@Test
	public void givenTwoDifferentAlgorithmPaddings_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.encryptAndDecryptUsingDifferentPaddings(key));
	}
	
	@Test
	public void givenTwoDifferentKeys_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.encryptAndDecryptUsingDifferentKeys());
	}
	
	@Test
	public void givenTwoDifferentAlgorithms_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.encryptAndDecryptUsingDifferentAlgorithms(key));
	}	
	
	@Test
	public void givenPlainText_whenUsingSameVariablesForEncrpytAndDecrypt_thenNoExceptionIsThrown() throws GeneralSecurityException
	{
		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		BadPadding.encryptAndDecryptUsingSamePaddingKeyAndAlgorithm(key, bytes);
		String decryptedText = new String(bytes);
		
		Assert.assertEquals(plainText, decryptedText);
	}	
}
