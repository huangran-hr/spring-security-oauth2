package tk.mybatis.mapper.common;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: HR
 * @Date: 2020/4/14 11:31
 * @Description:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
