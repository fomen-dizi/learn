package com.jys.weibo.service;

import com.jys.weibo.model.AyUserRoleRel;

import java.util.List;

/**
 * 描述：用户角色关联service
 */
public interface AyUserRoleRelService {
    List<AyUserRoleRel> findByUserId(String userId);
}
