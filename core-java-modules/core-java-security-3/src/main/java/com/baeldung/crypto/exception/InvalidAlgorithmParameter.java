package com.baeldung.crypto.exception;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.baeldung.crypto.utils.CryptoUtils;

public class InvalidAlgorithmParameter {

	public static void encryptUsingIv(byte[] ivBytes) throws InvalidKeyException, GeneralSecurityException
	{
		SecretKey encryptionKey = CryptoUtils.getFixedKey();	
		
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

		String plainText = "12345678901234567890";
		byte[] bytes = plainText.getBytes();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, ivParameterSpec);
		byte[] cipherTextBytes = cipher.doFinal(bytes);
	}
}
