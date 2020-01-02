package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className AlbumController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/19  16:14
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/allAlbum")
    public Map<String, Object> allAlbum(Integer page, Integer rows) {
        Map<String, Object> map = albumService.allAlbum(page, rows);
        return map;
    }

    @RequestMapping("/change")
    public Map<String, Object> change(String oper, Album album) {
        System.out.println(oper + ">>>>>>>>>>>>>>>>>>>>>>");
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            albumService.insertAlbum(album);
            System.out.println("====================已执行完添加方法==============");
            System.out.println("====================" + album.getId() + "===============");
            map.put("data", album.getId());
        }
        if (oper.equals("edit")) {
            //将图片路径设为null
            album.setCover(null);
            albumService.updateAlbum(album);
            System.out.println("=====================已经执行完修改方法============");
            map.put("data", album.getId());
        }
        if (oper.equals("del")) {
            albumService.deleteAlbum(album);
            System.out.println("=====================已经执行完修改方法============");
        }
        map.put("status", true);
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String id, MultipartFile cover, HttpSession session) throws IOException {
        System.out.println("id===========================>>>>" + id);
        System.out.println("cover===========================>>>>" + cover.getOriginalFilename());
        String name = UUID.randomUUID().toString() + cover.getOriginalFilename();
        // 文件上传
        cover.transferTo(new File(session.getServletContext().getRealPath("/image"), name));
        //如果不存在，则上传
        /*if (!cover==null) {
            cover.transferTo(file);
        }*/
        Album album = new Album();
        album.setId(id);
        album.setCover(name);
        albumService.updateAlbum(album);
    }
}
