package com.jys.weibo.controller.test;

import com.jys.weibo.model.test.Article;
import com.jys.weibo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/save")
    public Object save(long id, String title) {

        Article article = new Article();
        article.setId(id);
        article.setPv(123);
        article.setContent("Springboot整合Elasticsearch");
        article.setTitle(title);
        article.setSummary("搜索框架整合");
        articleRepository.save(article);
        return "save";
    }

    /*@RequestMapping("/search")
    public String search(@RequestParam("name") String title,Model model) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);
        Iterable<Article> list = articleRepository.search(queryBuilder);
        List<Article> articleList = Lists.newArrayList(list);
        model.addAttribute("wblist",articleList);
        return "search";
    }*/




}