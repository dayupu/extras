package org.dayup.kits.avatar.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "org.dayup.kits.avatar")
@EnableJpaRepositories(basePackages = "org.dayup.kits.avatar.jpa.repository")
public class StartApplication {


    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
