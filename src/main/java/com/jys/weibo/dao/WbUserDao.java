package com.jys.weibo.dao;

import com.jys.weibo.model.WbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface WbUserDao {
    WbUser findUserById(String userid);

    int insertUser(WbUser wbUser);

    WbUser findUserByEmailOrNameOrTel(@Param("userEmail") String email, @Param("userName") String name, @Param("userTel")
            int tel, @Param("userPassword") String password);

}
