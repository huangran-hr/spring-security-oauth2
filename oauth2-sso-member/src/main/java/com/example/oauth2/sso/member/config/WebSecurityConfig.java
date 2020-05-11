package com.example.oauth2.sso.member.config;

import com.example.oauth2.sso.member.utils.EnvironmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EnvironmentUtils environmentUtils;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**","/member/index");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if ("local".equals(environmentUtils.getActiveProfile())) {
            http.authorizeRequests().anyRequest().permitAll();
        }else {
            http.logout().logoutSuccessUrl("http://localhost:8080/logout")
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable();
        }
    }
}

