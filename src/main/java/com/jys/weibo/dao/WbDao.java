package com.jys.weibo.dao;

import com.jys.weibo.model.Weibo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WbDao {
    /**
     * 描述：插入微博
     * @param weibo
     */
    /*Weibo*/void insertWb(Weibo weibo);

    /**
     * 描述：查询所有微博
     * @return
     */
    List<Weibo> findAllWb();

    String findImagePathById(int id);

    String findVideoPathById(int id);
}
