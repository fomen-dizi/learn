package com.jys.weibo.service;

import com.jys.weibo.model.Weibo;

import java.util.List;

public interface WbService {
    public List<Weibo> getWeiboItemListList();

    public void insertWb(Weibo weibo);

    public List<Weibo> findAllWb();

    public String findImagePathById(int id);

    public String findVideoPathById(int id);
}
