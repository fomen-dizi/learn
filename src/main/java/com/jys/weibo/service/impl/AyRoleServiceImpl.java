package com.jys.weibo.service.impl;

import com.jys.weibo.model.AyRole;
import com.jys.weibo.repository.AyRoleRepository;
import com.jys.weibo.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户角色Service
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {
    @Resource
    private AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(String id) {
        return ayRoleRepository.findById(id).get();
    }
}
