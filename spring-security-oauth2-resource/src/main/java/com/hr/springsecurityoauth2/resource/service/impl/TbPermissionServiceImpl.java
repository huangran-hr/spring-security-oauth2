package com.hr.springsecurityoauth2.resource.service.impl;

import com.hr.springsecurityoauth2.resource.domain.TbPermission;
import com.hr.springsecurityoauth2.resource.mapper.TbPermissionMapper;
import com.hr.springsecurityoauth2.resource.service.TbPermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }

    @Override
    public List<TbPermission> selectAll() {
        return tbPermissionMapper.selectAll();
    }
}
