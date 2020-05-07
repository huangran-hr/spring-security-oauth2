package com.hr.spring.security.oauth2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: HR
 * @Date: 2020/4/13 9:42
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 需要配置这个支持password模式
     * support password grant type
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
        * 基于内存
        * */
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin888")).roles("ADMIN")
//                .and()
//                .withUser("hr").password(passwordEncoder().encode("123456")).roles("USER");

        /*
        * 基于JDBC,使用自定义认证与授权
        * */
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web)  {
        // 将 check_token 暴露出去，否则资源服务器访问时报 403 错误
        web.ignoring().antMatchers("/oauth/check_token");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 必须配置，不然OAuth2的http配置不生效
                .requestMatchers()
                .antMatchers("/auth/login", "/authentication/form","/oauth/authorize")
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers("/auth/login", "/authentication/form","/auth/index")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                // 登录页面
                .loginPage("/auth/login")  //自定义标准登录界面
                // 登录处理url
                .loginProcessingUrl("/authentication/form"); //自定义表单请求路径
        http.httpBasic().disable();
    }
}
