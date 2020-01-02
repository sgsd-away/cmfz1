package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className ChapterController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/19  17:27
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/allChapter")
    public Map<String, Object> allChapter(String album_id, Integer page, Integer rows) {
        System.out.println(album_id);
        Map<String, Object> map = chapterService.allChapter(album_id, page, rows);
        return map;
    }

    @RequestMapping("/change")
    public Map<String, Object> change(String album_id, String oper, Chapter chapter) {
        System.out.println(oper + ">>>>>>>>>>>>>>>>>");
        System.out.println(chapter);
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            chapter.setAlbum_id(album_id);
            chapterService.insertChapter(chapter);
            System.out.println("====================已执行完添加方法==============");
            Album album = new Album();
            album.setId(album_id);
            Album album1 = albumService.OneAlbum(album);
            album1.setCount(album1.getCount() + 1);
            albumService.updateAlbum(album1);
            map.put("data", chapter.getId());
        }
        if (oper.equals("edit")) {
            //将图片路径设为null
            chapter.setCover(null);
            chapterService.updateChapter(chapter);
            System.out.println("=====================已经执行完修改方法============");
            map.put("data", chapter.getId());
        }
        if (oper.equals("del")) {
            chapterService.deleteChapter(chapter);
            System.out.println("=====================已经执行完删除方法============");
            Album album = new Album();
            album.setId(album_id);
            Album album1 = albumService.OneAlbum(album);
            album1.setCount(album1.getCount() - 1);
            albumService.updateAlbum(album1);
            System.out.println("=====================让章节的count减少一个============");
            map.put("data", chapter.getId());
        }
        map.put("status", true);
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String id, MultipartFile cover, HttpSession session) {
        //音频文件上传
        String name = UUID.randomUUID().toString() + cover.getOriginalFilename();
        File file = new File(session.getServletContext().getRealPath("/music"), name);
        try {
            cover.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改章节信息
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setCover(name);
        //保存文件的大小
        BigDecimal size = new BigDecimal(cover.getSize());
        BigDecimal dom = new BigDecimal(1024);
        BigDecimal bigDecimal = size.divide(dom).divide(dom).setScale(2, BigDecimal.ROUND_HALF_UP);
        chapter.setSize(bigDecimal + "MB");
        //计算文件的时长
        Encoder encoder = new Encoder();
        try {
            long duration = encoder.getInfo(file).getDuration();
            long second = duration / 1000 / 60;
            chapter.setDuration(duration / 1000 / 60 + ":" + duration / 1000 % 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chapterService.updateChapter(chapter);
    }
}
