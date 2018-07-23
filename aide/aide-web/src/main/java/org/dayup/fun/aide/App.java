package org.dayup.fun.aide;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ImportResource("META-INF/spring/application-root.xml")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);


//        File file = new File("/home/lcs/develop/github/extras/aide/aide-web/src/main/resources/static/refs/codemirror" +
//                "-5.39.2");
//
//        List<File> htmlFiles = new ArrayList<>();
//        findHtmlFile(file, htmlFiles);
//        for (File f : htmlFiles) {
//            f.delete();
//        }

    }

    public static void findHtmlFile(File file, List<File> htmlFiles) {
        if (file == null) {
            return;
        }

        if (file.isFile() && file.getName().endsWith(".html")) {
            htmlFiles.add(file);
            return;
        }

        if (file.listFiles() != null) {
            for (File subFile : file.listFiles()) {
                findHtmlFile(subFile, htmlFiles);
            }
        }

    }

}
