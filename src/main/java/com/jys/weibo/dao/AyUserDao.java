package com.jys.weibo.dao;

import com.jys.weibo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AyUserDao {
    /**
     * 通过用户名和密码查询用户
     * @param name
     * @param password
     * @return
     */
    AyUser findByNameAndPassword(@Param("name") String name,@Param("password") String password);
}
