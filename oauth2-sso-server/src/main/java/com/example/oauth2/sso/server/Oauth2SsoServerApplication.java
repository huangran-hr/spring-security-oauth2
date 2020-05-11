package com.example.oauth2.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.oauth2.sso.server.mapper")
public class Oauth2SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoServerApplication.class, args);
    }

}
