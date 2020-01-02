package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @className ArticleServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/22  10:04
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> allArticle(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleDAO.selectByRowBounds(article, rowBounds);
        int count = articleDAO.selectCount(article);
        map.put("rows", articles);
        map.put("page", page);
        map.put("records", count);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        return map;
    }

    @Override
    public void insertArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreate_date(new Date());
        int i = articleDAO.insertSelective(article);
        if (i == 0) {
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public void updateArticle(Article article) {
        int i = articleDAO.updateByPrimaryKeySelective(article);
        if (i == 0) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void deleteArticle(Article article) {
        int i = articleDAO.deleteByPrimaryKey(article);
        if (i == 0) {
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<Map<String, Object>> selectAllArtical() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleDAO.selectAll();
        for (Article article : articles) {
            map.put("thumbnail", "");
            map.put("title", article.getTitle());
            map.put("author", article.getAuthor());
            map.put("type", "1");
            map.put("set_count", "");
            map.put("create_date", article.getCreate_date());
            list.add(map);
        }
        return list;
    }
}
