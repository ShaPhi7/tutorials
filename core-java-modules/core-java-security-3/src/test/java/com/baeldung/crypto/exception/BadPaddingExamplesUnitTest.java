package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baeldung.crypto.utils.CryptoUtils;

public class BadPaddingExamplesUnitTest {

	private SecretKey key;
	
	@Before
	public void before() throws GeneralSecurityException
	{
		key = CryptoUtils.getFixedKey();
	}
	
	@Test
	public void givenTwoDifferentAlgorithmPaddings_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPaddingExamples.encryptAndDecryptUsingDifferentPaddings(key));
	}
	
	@Test
	public void givenTwoDifferentKeys_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPaddingExamples.encryptAndDecryptUsingDifferentKeys());
	}
	
	@Test
	public void givenTwoDifferentAlgorithms_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{	
		Assert.assertThrows(BadPaddingException.class, () -> BadPaddingExamples.encryptAndDecryptUsingDifferentAlgorithms(key));
	}	
	
	@Test
	public void givenPlainText_whenUsingSameVariablesForEncrpytAndDecrypt_thenNoExceptionIsThrown() throws GeneralSecurityException
	{
		String plainText = "https://www.baeldung.com/";
		byte[] bytes = plainText.getBytes();
		
		BadPaddingExamples.encryptAndDecryptUsingSamePaddingKeyAndAlgorithm(key, bytes);
		String decryptedText = new String(bytes);
		
		Assert.assertEquals(plainText, decryptedText);
	}	
}
