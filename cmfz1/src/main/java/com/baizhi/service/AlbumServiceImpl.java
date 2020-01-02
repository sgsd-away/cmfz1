package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @className AlbumServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/19  16:05
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    public Map<String, Object> allAlbum(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Album a = new Album();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> albums = albumDAO.selectByRowBounds(a, rowBounds);
        int i = albumDAO.selectCount(a);
        Integer total = i % rows == 0 ? i / rows : i / rows + 1;
        map.put("rows", albums);
        map.put("page", page);
        map.put("records", i);
        map.put("total", total);
        return map;
    }

    @Override
    public void insertAlbum(Album album) {
        Example e = new Example(Chapter.class);
        e.createCriteria().andEqualTo("album_id", album.getId());
        List<Chapter> chapters = chapterDAO.selectByExample(e);
        int count = chapters.size();
        album.setId(UUID.randomUUID().toString());
        album.setCreate_date(new Date());
        album.setCount(count);
        albumDAO.insertSelective(album);
    }

    @Override
    public void updateAlbum(Album album) {
        albumDAO.updateByPrimaryKeySelective(album);
    }

    @Override
    public Album OneAlbum(Album album) {
        Album album1 = albumDAO.selectOne(album);
        return album1;
    }

    @Override
    public void deleteAlbum(Album album) {
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(album.getId());
        List<Chapter> chapters = chapterDAO.select(chapter);
        for (Chapter c : chapters) {
            chapterDAO.deleteByPrimaryKey(c);
        }
        albumDAO.deleteByPrimaryKey(album);
    }

    @Override
    public List<Map<String, Object>> selectAllAlbum() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Album> albums = albumDAO.selectAll();
        for (Album album : albums) {
            map.put("thumbnail", album.getCover());
            map.put("title", album.getTitle());
            map.put("author", "");
            map.put("type", "0");
            map.put("set_count", album.getCount());
            map.put("create_date", album.getCreate_date());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectWenAlbum() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Album> albums = albumDAO.selectAll();
        for (Album album : albums) {
            map.put("thumbnail", album.getCover());
            map.put("title", album.getTitle());
            map.put("score", album.getScore());
            map.put("author", album.getAuthor());
            map.put("broadcast", album.getBeam());
            map.put("set_count", album.getCount());
            map.put("brief", album.getIntro());
            map.put("create_date", album.getCreate_date());
            list.add(map);
        }
        return list;
    }
}
