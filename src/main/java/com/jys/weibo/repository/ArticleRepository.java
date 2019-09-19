package com.jys.weibo.repository;

import com.jys.weibo.model.test.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}