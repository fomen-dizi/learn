package com.jys.weibo.service;

import com.jys.weibo.model.WbUser;

public interface WbUserService {
    int insertWbUser(WbUser wbUser);

    WbUser findUserByEmailOrNameOrTel(String email,String name,int tel,String password);

}
