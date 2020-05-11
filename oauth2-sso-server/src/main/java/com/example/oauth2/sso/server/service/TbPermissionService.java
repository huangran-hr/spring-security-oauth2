package com.example.oauth2.sso.server.service;


import com.example.oauth2.sso.server.domain.TbPermission;
import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
public interface TbPermissionService{

    List<TbPermission> selectByUserId(Long userId);
}
