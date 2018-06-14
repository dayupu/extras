package org.dayup.avatar.app;


import org.dayup.avatar.support.fool.FoolRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.dayup.avatar"})
public class StartApplication {


    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
