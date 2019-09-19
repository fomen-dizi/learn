package com.jys.weibo.repository;

import com.jys.weibo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AyUserRepository extends JpaRepository<AyUser,String> {

    /**
     * 通過名字相等查詢，參數為name
     * @param name
     * @return
     */
    List<AyUser> findByName(String name);

    /**
     * 通过名字like查询，参数为name.
     * @param name
     * @return
     */
    List<AyUser> findByNameLike(String name);

    /**
     * 通过主键id集合查询，参数为id集合
     * @param ids
     * @return
     */
    List<AyUser> findByIdIn(Collection<String> ids);
}
