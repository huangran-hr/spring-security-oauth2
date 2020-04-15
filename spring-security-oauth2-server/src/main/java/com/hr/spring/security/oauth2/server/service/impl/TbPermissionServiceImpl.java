package com.hr.spring.security.oauth2.server.service.impl;

import com.hr.spring.security.oauth2.server.domain.TbPermission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hr.spring.security.oauth2.server.mapper.TbPermissionMapper;
import com.hr.spring.security.oauth2.server.service.TbPermissionService;

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
}
