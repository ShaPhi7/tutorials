package com.baeldung.crypto.utils;

import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

    public static SecretKey generateKey() throws GeneralSecurityException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    
    /*
     * Allows us to generate a deterministic key, for the purposes of producing reliable
     * and consistent demo code! For a random key, consider using the generateKey method above.
     */
    public static SecretKey getKeyForText(String secretText) throws GeneralSecurityException {
    	byte[] keyBytes = secretText.getBytes(StandardCharsets.UTF_8);
    	return new SecretKeySpec(keyBytes, "AES"); 
    }
    
    /*
     * Normally you would generate a key as per above, however
     * we'll use this method to get the same key repeatedly and ensure
     * that our test methods always run the same way and are deterministic.
     */
    public static SecretKey getFixedKey() throws GeneralSecurityException {
    	String secretText = "BaeldungIsASuperCoolSite";
    	return getKeyForText(secretText);
    }

    public static IvParameterSpec getIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[128 / 8];
        byte[] nonce = new byte[96 / 8];
        secureRandom.nextBytes(nonce);
        System.arraycopy(nonce, 0, iv, 0, nonce.length);
        return new IvParameterSpec(nonce);
    }

    public static IvParameterSpec getIVSecureRandom(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] iv = new byte[Cipher.getInstance(algorithm)
            .getBlockSize()];
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec getIVInternal(Cipher cipher) throws InvalidParameterSpecException {
        AlgorithmParameters params = cipher.getParameters();
        byte[] iv = params.getParameterSpec(IvParameterSpec.class)
            .getIV();
        return new IvParameterSpec(iv);
    }

    public static byte[] getRandomIVWithSize(int size) {
        byte[] nonce = new byte[size];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

}