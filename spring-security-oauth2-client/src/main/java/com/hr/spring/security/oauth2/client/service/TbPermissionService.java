package com.hr.spring.security.oauth2.client.service;

import com.hr.spring.security.oauth2.client.domain.TbPermission;

import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
public interface TbPermissionService{

    List<TbPermission> selectByUserId(Long userId);
}
