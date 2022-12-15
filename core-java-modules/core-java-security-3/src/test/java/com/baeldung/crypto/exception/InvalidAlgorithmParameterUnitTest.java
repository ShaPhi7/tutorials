package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
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

public class InvalidAlgorithmParameterUnitTest {

	private SecretKey key;
	
	@Before
	public void generateKey() throws GeneralSecurityException
	{
		//key = CryptoUtils.generateKey();
	}
	
	@Test
	public void whenEncryptingWithIvTooShort_thenInvalidAlgorithmParameterExceptionIsThrown() throws GeneralSecurityException
	{
		byte[] ivBytes = new byte[]{'B', 'a', 'e', 'l', 'd', 'u', 'n', 'g', 'I', 's', 'G', 'r', 'e', 'a', 't'};
		Assert.assertThrows(InvalidAlgorithmParameterException.class, () -> InvalidAlgorithmParameter.encryptUsingIv(ivBytes));
	}
	
	@Test
	public void whenEncryptingWithIvTooLong_thenInvalidAlgorithmParameterExceptionIsThrown() throws GeneralSecurityException
	{
		byte[] ivBytes = new byte[]{'B', 'a', 'e', 'l', 'd', 'u', 'n', 'g', 'I', 's', 'G', 'r', 'e', 'a', 't', '!', '?'};
		Assert.assertThrows(InvalidAlgorithmParameterException.class, () -> InvalidAlgorithmParameter.encryptUsingIv(ivBytes));
	}
	
	@Test
	public void whenEncryptingWithIvCorrectSize_thenNoExceptionIsThrown() throws GeneralSecurityException
	{
		byte[] ivBytes = new byte[]{'B', 'a', 'e', 'l', 'd', 'u', 'n', 'g', 'I', 's', 'G', 'r', 'e', 'a', 't', '!'};
		InvalidAlgorithmParameter.encryptUsingIv(ivBytes);
	}
	
}
