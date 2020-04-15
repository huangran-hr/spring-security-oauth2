package com.hr.spring.security.oauth2.client.service.impl;

import com.hr.spring.security.oauth2.client.domain.TbPermission;
import com.hr.spring.security.oauth2.client.mapper.TbPermissionMapper;
import com.hr.spring.security.oauth2.client.service.TbPermissionService;
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
}
