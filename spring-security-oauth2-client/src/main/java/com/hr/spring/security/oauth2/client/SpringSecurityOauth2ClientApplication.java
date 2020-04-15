package com.hr.spring.security.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hr.spring.security.oauth2.client.mapper")
public class SpringSecurityOauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2ClientApplication.class, args);
    }

}
