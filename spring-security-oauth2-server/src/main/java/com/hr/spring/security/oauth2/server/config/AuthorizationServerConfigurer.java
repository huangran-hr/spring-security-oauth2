package com.hr.spring.security.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @Auther: HR
 * @Date: 2020/4/13 10:29
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 使用自己配置的数据源
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置token
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource());
    }

    /**
     * clientDetails客户端配置
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService(){
        //从数据库读取客户端配置
        return new JdbcClientDetailsService(dataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()); //写入token
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
        * 基于内存
        * */
//        clients.inMemory().withClient("client")
//                .secret(passwordEncoder.encode("secret"))
//                .authorizedGrantTypes("authorization_code")  //授权类型，授权码模式，可多个
//                .scopes("app")
//                .redirectUris("https://www.baidu.com");  //回调url


        /*
        * 基于JDBC
        * */
        clients.withClientDetails(jdbcClientDetailsService());

    }
}
