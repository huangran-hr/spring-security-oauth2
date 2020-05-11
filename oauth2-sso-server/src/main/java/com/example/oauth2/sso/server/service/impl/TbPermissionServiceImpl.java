package com.example.oauth2.sso.server.service.impl;

import com.example.oauth2.sso.server.domain.TbPermission;
import com.example.oauth2.sso.server.mapper.TbPermissionMapper;
import com.example.oauth2.sso.server.service.TbPermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }
}
