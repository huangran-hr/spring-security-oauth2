package com.hr.springsecurityoauth2.resource.service;

import com.hr.springsecurityoauth2.resource.domain.TbPermission;
import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
public interface TbPermissionService{

    List<TbPermission> selectByUserId(Long userId);

    List<TbPermission> selectAll();
}
