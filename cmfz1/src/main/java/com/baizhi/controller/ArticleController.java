package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @className ArticleController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/22  10:12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/allArticle")
    public Map<String, Object> allArticle(Integer page, Integer rows) {
        Map<String, Object> map = articleService.allArticle(page, rows);
        return map;
    }

    @RequestMapping("/change")
    public Map<String, Object> change(String oper, Article article) {
        // System.out.println(oper+article);
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            try {
                articleService.insertArticle(article);
                map.put("status", true);
                map.put("message", "添加成功");
            } catch (Exception e) {
                map.put("status", false);
                map.put("message", "添加失敗");
            }
        }
        if (oper.equals("edit")) {
            try {
                articleService.updateArticle(article);
                map.put("status", true);
                map.put("message", "修改成功");
            } catch (Exception e) {
                map.put("status", false);
                map.put("message", "修改失敗");
            }
        }
        if (oper.equals("del")) {
            try {
                articleService.deleteArticle(article);
                map.put("status", true);
                map.put("message", "刪除成功");
            } catch (Exception e) {
                map.put("status", false);
                map.put("message", "刪除失敗");
            }
        }
        return map;
    }
}
