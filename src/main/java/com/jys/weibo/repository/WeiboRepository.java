package com.jys.weibo.repository;

import com.jys.weibo.model.Weibo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface WeiboRepository extends ElasticsearchRepository<Weibo, Integer> {
}