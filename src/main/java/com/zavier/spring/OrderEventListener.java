package com.zavier.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
@Component
public class OrderEventListener {

    public static void main1(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String plainText = "要加密的明文";

        Key secretKey = getKey("password");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] p = plainText.getBytes("UTF-8");
        byte[] result = cipher.doFinal(p);
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(result);

        System.out.println("加密后:" + encoded);
    }


    public static void main(String[] args) throws Exception {
        // 这是密文
        String cipherText = "+GjVjs4RggXB/xao4gYHNa1avmD6112Jt0/JjabLkQE=";

        Key secretKey = getKey("password");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] c = decoder.decodeBuffer(cipherText);
        byte[] result = cipher.doFinal(c);
        String plainText = new String(result, "UTF-8");

        System.out.println("明文:" + plainText);

    }

    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
