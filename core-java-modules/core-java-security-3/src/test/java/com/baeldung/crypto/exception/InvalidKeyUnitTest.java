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

public class InvalidKeyUnitTest {

	private SecretKey key;
	
	@Before
	public void before() throws GeneralSecurityException
	{
		//key = CryptoUtils.getFixedKey();
	}
	
	@Test
	public void givenTextEncryptedWithCBC_whenDecryptingWithNoIv_thenInvalidKeyExceptionIsThrown()
	{
		Assert.assertThrows(InvalidKeyException.class, () -> InvalidKey.decryptUsingCBCWithNoIV());
	}
	
	@Test
	public void givenTextEncryptedWithCBC_whenDecryptingWithIv_thenTextIsDecrypted() throws InvalidKeyException, GeneralSecurityException
	{
		InvalidKey.decryptUsingCBCWithIV();
	}
	
	@Test
	public void givenTextEncryptedWithCBC_whenKeyIsTooShort_thenInvalidKeyExceptionIsThrown()
	{
		Assert.assertThrows(InvalidKeyException.class, () -> InvalidKey.encryptWithKeyTooShort());
	}
	
	@Test
	public void givenTextEncryptedWithCBC_whenKeyIsTooLong_thenInvalidKeyExceptionIsThrown()
	{
		Assert.assertThrows(InvalidKeyException.class, () -> InvalidKey.encryptWithKeyTooLong());
	}
	
}
