package org.dayup.avatar.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.dayup.avatar"})
public class StartApplication {


    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
