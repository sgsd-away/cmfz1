package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public Map<String, Object> allArticle(Integer page, Integer rows);

    public void insertArticle(Article article);

    public void updateArticle(Article article);

    public void deleteArticle(Article article);

    public List<Map<String, Object>> selectAllArtical();
}
