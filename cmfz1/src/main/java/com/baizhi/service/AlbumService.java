package com.baizhi.service;


import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Map<String, Object> allAlbum(Integer page, Integer rows);

    public void insertAlbum(Album album);

    public void updateAlbum(Album album);

    public Album OneAlbum(Album album);

    public void deleteAlbum(Album album);

    public List<Map<String, Object>> selectAllAlbum();

    public List<Map<String, Object>> selectWenAlbum();
}
