package com.example.oauth2.sso.server.service.impl;

import com.example.oauth2.sso.server.domain.TbUser;
import com.example.oauth2.sso.server.mapper.TbUserMapper;
import com.example.oauth2.sso.server.service.TbUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import tk.mybatis.mapper.entity.Example;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username",username);
        return tbUserMapper.selectOneByExample(example);
    }
}
