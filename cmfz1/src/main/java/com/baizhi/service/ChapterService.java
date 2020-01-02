package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

/**
 * @className ChapterService
 * @Description
 * @Authour 官帅
 * @Date 2019/12/19  17:21
 */
public interface ChapterService {
    public Map<String, Object> allChapter(String id, Integer page, Integer rows);

    public void insertChapter(Chapter chapter);

    public void updateChapter(Chapter chapter);

    public void deleteChapter(Chapter chapter);

    public List<Map<String, Object>> selectAllChapter();
}
