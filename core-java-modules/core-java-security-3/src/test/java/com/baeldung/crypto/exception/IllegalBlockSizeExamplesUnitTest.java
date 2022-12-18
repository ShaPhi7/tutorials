package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baeldung.crypto.utils.CryptoUtils;

public class IllegalBlockSizeExamplesUnitTest {

	private SecretKey key;
	
	@Before
	public void before() throws GeneralSecurityException
	{
		key = CryptoUtils.getFixedKey();
	}
	
	@Test
	public void whenEncryptingPlainTextWithoutPadding_thenIllegalBlockSizeExceptionIsThrown() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{	
		Assert.assertThrows(IllegalBlockSizeException.class, () -> IllegalBlockSizeExamples.encryptWithoutPadding(key));
	}
	
	@Test
	public void whenDecryptingCipherTextThatWasNotEncrypted_thenIllegalBlockSizeExceptionIsThrown() throws GeneralSecurityException
	{	
		//note that the bytes we're passing into our decrypt method are not encrypted.
		Assert.assertThrows(IllegalBlockSizeException.class, () -> IllegalBlockSizeExamples.decryptTextThatIsNotEncrypted(key));
	}
	
	@Test
	public void GivenEncryptedPlainText_whenDecrypting_thenReturnsPlainText() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		String plainText = "12345678901234567890";
		byte[] plainTextBytes = plainText.getBytes();
		
		//TODO - move to a utils class?
		byte[] cipherTextBytes = IllegalBlockSizeExamples.encryptWithPadding(key, plainTextBytes);
		
		byte[] decryptedBytes = IllegalBlockSizeExamples.decryptWithPadding(key, cipherTextBytes);
		String decryptedText = new String(decryptedBytes);
		
		Assert.assertEquals(plainText, decryptedText);
	}
}
