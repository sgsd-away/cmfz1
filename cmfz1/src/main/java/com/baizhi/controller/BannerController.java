package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className BannerController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/18  16:46
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/allBanner")
    public @ResponseBody
    Map<String, Object> allBanner(Integer page, Integer rows) {
        System.out.println(page + "&&&&&&&&&&&&&&&&&&&" + rows);
        Map<String, Object> map = new HashMap<>();
        //查询当前查询到的结果集
        map.put("rows", bannerService.allBanner(page, rows));
        //添加当前页的页码
        map.put("page", page);
        //添加总页数
        map.put("total", bannerService.totalPage(rows));
        //添加总条数
        map.put("records", bannerService.totalSize());
        return map;
    }

    @RequestMapping("/change")
    public @ResponseBody
    Map<String, Object> change(String oper, Banner banner) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            //banner.setCover(null);
            bannerService.insertBanner(banner);
            System.out.println("====================已执行完添加方法==============");
            map.put("data", banner.getId());
        } else if (oper.equals("edit")) {
            //将图片路径设为null
            banner.setCover(null);
            bannerService.updateBanner(banner);
            System.out.println("=====================已经执行完修改方法============");
            map.put("data", banner.getId());
        } else if (oper.equals("del")) {
            bannerService.deleteBanner(banner);
            System.out.println("=====================已经执行完删除方法============");
            map.put("data", banner.getId());
        }
        map.put("status", true);
        return map;
    }

    @RequestMapping("/upload")
    public @ResponseBody
    void upload(String id, MultipartFile cover, HttpSession session) throws IOException {
        System.out.println("id===========================>>" + id);
        System.out.println("cover===========================>>" + cover.getOriginalFilename());
        String name = UUID.randomUUID().toString() + cover.getOriginalFilename();
        // 文件上传
        cover.transferTo(new File(session.getServletContext().getRealPath("/image"), name));
        //如果不存在，则上传
        /*if (!cover==null) {
            cover.transferTo(file);
        }*/
        Banner banner = new Banner();
        banner.setId(id);
        banner.setCover(name);
        bannerService.updateBanner(banner);
    }
}
