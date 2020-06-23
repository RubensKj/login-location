package com.rubenskj.security.loginlocation;

import com.rubenskj.security.loginlocation.property.DbMaxMindProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({DbMaxMindProperty.class})
public class LoginLocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginLocationApplication.class, args);
    }

}
