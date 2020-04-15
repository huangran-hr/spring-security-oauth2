package com.hr.spring.security.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.hr.spring.security.oauth2.server.mapper")
public class SpringSecurityOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2ServerApplication.class, args);
    }

}
