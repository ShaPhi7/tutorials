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

public class NoSuchXUnitTest {
	
	@Test
	public void whenInitingCipherWithUnknownAlgorithm_thenNoSuchAlgorithmExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(NoSuchAlgorithmException.class, () -> NoSuchX.getCipherInstanceWithBadAlgorithm());
	}
	
	@Test
	public void whenInitingCipherWithUnknownAlgorithmMode_thenNoSuchAlgorithmExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(NoSuchAlgorithmException.class, () -> NoSuchX.getCipherInstanceWithBadAlgorithmMode());
	}
	
	@Test
	public void whenInitingCipherWithUnknownPadding_thenNoSuchAlgorithmExceptionIsThrown() throws GeneralSecurityException
	{
		Assert.assertThrows(NoSuchAlgorithmException.class, () -> NoSuchX.getCipherInstanceWithBadPadding());
	}
	
	@Test
	public void whenInitingCipherWithValidAlgorithm_thenCipherInstanceIsReturned() throws GeneralSecurityException
	{
		Assert.assertTrue(NoSuchX.getCipherInstanceWithValidAlgorithm() instanceof Cipher);
	}
}
