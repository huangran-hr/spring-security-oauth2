package com.example.oauth2.sso.server.service;


import com.example.oauth2.sso.server.domain.TbUser;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
public interface TbUserService{

    TbUser getByUsername(String username);

}
