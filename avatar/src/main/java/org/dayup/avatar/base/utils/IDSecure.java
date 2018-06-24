
package org.dayup.avatar.base.utils;

import org.apache.commons.codec.binary.Base32;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public final class IDSecure {

    private static final String UTF8 = "UTF-8";
    private static Integer minLen = 8;
    private static Base32 base32 = new Base32();
    private static char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String encode(Long id) {

        String idStr = String.valueOf(id);
        int lackLen = minLen - idStr.length();
        if (lackLen > 0) {
            Random random = new Random();
            String lackStr = "";
            while (lackLen > 0) {
                lackStr += letters[random.nextInt(letters.length)];
                lackLen--;
            }
            idStr += lackStr;
        }
        try {
            return base32.encodeAsString(idStr.getBytes(UTF8)).replaceAll("=", "").toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long decode(String encodeStr) {

        try {
            String idStr = new String(base32.decode(encodeStr.toUpperCase()), UTF8);
            return Long.parseLong(idStr.replaceAll("[a-zA-Z]", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
