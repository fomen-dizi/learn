package com.jys.weibo.service.impl;

import com.jys.weibo.dao.WbUserDao;
import com.jys.weibo.model.WbUser;
import com.jys.weibo.service.WbUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WbUserServiceImpl implements WbUserService {
    @Resource
    WbUserDao wbUserDao;
    @Override
    public int insertWbUser(WbUser wbUser) {
        int id = wbUserDao.insertUser(wbUser);
        int ids = wbUser.getId();
        return ids;
    }

    @Override
    public WbUser findUserByEmailOrNameOrTel(String email, String name, int tel,String password) {

        return wbUserDao.findUserByEmailOrNameOrTel(email,name,tel,password);
    }
}
