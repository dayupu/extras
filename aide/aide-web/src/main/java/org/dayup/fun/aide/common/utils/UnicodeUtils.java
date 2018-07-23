package org.dayup.fun.aide.common.utils;

public class UnicodeUtils {
    /*
     * 中文转unicode编码
     */
    public static String encode(final String text) {
        char[] utfBytes = text.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }

            builder.append("\\u" + hexB);
        }
        return builder.toString();
    }

    /*
     * unicode编码转中文
     */
    public static String decode(final String enText) {
        int start = 0;
        int end = 0;
        StringBuilder buffer = new StringBuilder();
        while (start > -1) {
            end = enText.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = enText.substring(start + 2, enText.length());
            } else {
                charStr = enText.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
