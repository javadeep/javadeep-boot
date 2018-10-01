package com.javadeep.boot.validator;

import com.javadeep.boot.validator.annotation.EnableJavadeepValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableJavadeepValidator(value = "com.javadeep.boot.validator", basePackages = "com.javadeep.boot.validator")
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
