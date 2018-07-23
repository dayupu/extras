package org.dayup.fun.aide.common.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class EncryptUtils {

    private final static String UTF8 = "utf-8";

    public static String AES(final String text, final String password, boolean isEncrypt) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes(UTF8)));// 利用用户密码作为随机数初始化出
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            byte[] result;
            if (isEncrypt) {
                byte[] textBytes = text.getBytes(UTF8);
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
                result = cipher.doFinal(textBytes);
            } else {
                byte[] textBytes = hexStringToBytes(text);
                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
                result = cipher.doFinal(textBytes);
            }
            return bytesToHexString(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String SHA(final String text, final String algorithm) {
        // 返回值
        String strResult = null;
        // 是否是有效字符串
        if (text != null && text.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                messageDigest.update(text.getBytes(UTF8));
                byte[] result = messageDigest.digest();
                return bytesToHexString(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return strResult;
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
