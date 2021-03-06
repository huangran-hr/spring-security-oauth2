package com.hr.springsecurityoauth2.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //// 以下为配置所需保护的资源路径及权限，需要与认证服务器配置的授权部分对应，也就是tb_permission中的数据
                .mvcMatchers("/").hasAuthority("root")
                .mvcMatchers("/system/").hasAuthority("system")
                .mvcMatchers("/permission/").hasAuthority("permission")
                .mvcMatchers("/permission/view/**").hasAuthority("permissionView");
    }
}
