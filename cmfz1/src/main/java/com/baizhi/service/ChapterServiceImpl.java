package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @className ChapterServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/19  17:22
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public Map<String, Object> allChapter(String album_id, Integer page, Integer rows) {
        Chapter chapter = new Chapter();
        Map<String, Object> map = new HashMap<>();
        Example e = new Example(Chapter.class);
        e.createCriteria().andEqualTo("album_id", album_id);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Chapter> chapters = chapterDAO.selectByExampleAndRowBounds(e, rowBounds);
        chapterDAO.selectByExampleAndRowBounds(e, rowBounds);
        int i = chapterDAO.selectCount(chapter);
        Integer total = i % rows == 0 ? i / rows : i / rows + 1;
        map.put("rows", chapters);
        map.put("page", page);
        map.put("records", i);
        map.put("total", total);
        return map;
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreate_date(new Date());
        //计算出音频文件的大小转换成字符串存入到chapter对象当中
        //计算出音频文件的时长转换成字符串存入到chapter对象当中
        chapterDAO.insertSelective(chapter);
    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapterDAO.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public void deleteChapter(Chapter chapter) {
        chapterDAO.deleteByPrimaryKey(chapter);
    }

    @Override
    public List<Map<String, Object>> selectAllChapter() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Chapter> chapters = chapterDAO.selectAll();
        for (Chapter chapter : chapters) {
            map.put("title", chapter.getTitle());
            map.put("download_url", chapter.getCover());
            map.put("size", chapter.getSize());
            map.put("duration", chapter.getDuration());
            list.add(map);
        }
        return list;
    }
}
