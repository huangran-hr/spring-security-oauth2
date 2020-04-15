package com.hr.spring.security.oauth2.client.service.impl;

import com.hr.spring.security.oauth2.client.domain.TbUser;
import com.hr.spring.security.oauth2.client.mapper.TbUserMapper;
import com.hr.spring.security.oauth2.client.service.TbUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
@Service
public class TbUserServiceImpl implements TbUserService{

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username",username);
        return tbUserMapper.selectOneByExample(example);
    }
}
