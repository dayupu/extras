package org.dayup.fun.aide.common.utils;

public class ASCIIUtils {

    /**
     * 字符串转换为Ascii
     *
     * @param value
     * @return
     */
    public static String encode(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();

    }

    /**
     * Ascii转换为字符串
     *
     * @param value
     * @return
     */
    public static String decode(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
}
