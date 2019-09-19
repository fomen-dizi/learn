package com.jys.weibo.service;

import com.jys.weibo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 用戶服務層接口
 */
public interface AyUserService {
    AyUser findById(String id);

    List<AyUser> findAll();

    AyUser save(AyUser ayUser);

    void delete(String id);

    //分頁
    Page<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);

    AyUser findByNameAndPassword(String name,String password);

    Future<List<AyUser>> findAsynAll();

    AyUser findByNameAndPasswordRetry(String name,String password);

    AyUser findByUserName(String name);
}
