
package org.dayup.avatar.base.utils;

import org.springframework.util.ResourceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUtil {

    private static final String MARK_LINE = System.lineSeparator();


    public static String readFile(String classPath) {

        try {
            File file = ResourceUtils.getFile("classpath:" + classPath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(MARK_LINE);
            }
            return builder.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
