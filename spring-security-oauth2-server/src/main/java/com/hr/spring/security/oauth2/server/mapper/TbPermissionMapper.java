package com.hr.spring.security.oauth2.server.mapper;

import com.hr.spring.security.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.MyMapper;

import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:10
 * @Description:
 */
public interface TbPermissionMapper extends MyMapper<TbPermission> {
    List<TbPermission> selectByUserId(@Param("userId") Long userId);
}