package com.jys.weibo.service.impl;

import com.jys.weibo.model.AyUserRoleRel;
import com.jys.weibo.repository.AyUserRoleRelRepository;
import com.jys.weibo.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：用户角色关联service
 */
@Service
public class AyUserRoleRelServiceImpl implements AyUserRoleRelService {
    @Resource
    private AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}
