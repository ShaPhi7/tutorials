package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

public class BadPaddingUnitTest {

	private SecretKey key;
	
	@Before
	public void generateKey() throws GeneralSecurityException
	{
		//key = CryptoUtils.generateKey();
	}
	
	@Test
	public void givenTwoDifferentKeys_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.usingWrongKeys());
	}
	
	@Test
	public void givenTwoDifferentAlgorithms_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.usingWrongAlgorithm());
	}
	
	@Test
	public void givenTwoDifferentAlgorithmPaddings_whenDecrypting_thenBadPaddingExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(BadPaddingException.class, () -> BadPadding.usingWrongPadding());
	}
	
}
