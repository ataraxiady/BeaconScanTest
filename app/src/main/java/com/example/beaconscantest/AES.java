package com.example.beaconscantest;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import timber.log.Timber;



/**
 * 양방향 AES 암호화
 *
 * @author : jaehyun
 * <p>
 * Date: 2019-04-10
 * Copyright(©) 2019 by ATOSTUDY.
 */

public class AES {

    // 32byte (256bit)
    private static final String secretKey = "8uyqEdrFYknK8FKMeqV0Uyf5dWhObwil";
    private static AES instance = new AES();
    // 24byte (192bit)
//	final String secretKey = "8uyqEdrFYknK8FKMeqV0Uyf5";
    // 16byte (128bit)
//	final String secretKey = "8uyqEdrFYknK8FKM";
    private static String IV = null;

    private static SecretKey secureKeySpec;

    static {
        try {
            Timber.d("=== AES instance create ===");

            IV = secretKey.substring(0, 16);

            secureKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        } catch (Exception e) {
            Timber.e(e.getMessage(), e);
        }

    }

    private AES() {
    }

    public static AES getInstance() {

        return instance;
    }


    /**
     * 문자열 값을 암호화
     *
     * @param str 암호할 값
     * @return String
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String encrypt(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Timber.d("encrypt input value [{}]" + str);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKeySpec, new IvParameterSpec(IV.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encode(encrypted, Base64.DEFAULT));

        return enStr;
    }

    /**
     * 암호화한 값을 복호화
     *
     * @param str 복호화할 값
     * @return String
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String decrypt(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Timber.d("decrypt input value [{}]" + str);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKeySpec, new IvParameterSpec(IV.getBytes("UTF-8")));

        byte[] byteStr = Base64.decode(str.getBytes(), Base64.DEFAULT);

        return new String(c.doFinal(byteStr), "UTF-8");
    }

}
