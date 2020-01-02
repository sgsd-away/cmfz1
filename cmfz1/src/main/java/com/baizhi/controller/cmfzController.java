package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @className cmfzController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/26  16:55
 */
@RestController
@RequestMapping("/cmfz")
public class cmfzController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;

    @RequestMapping("/first_page")
    public Map<String, Object> first_page(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        if (uid == null || type == null) {
            map.put("error", "参数不能为空");
        } else {
            if (type.equals("all")) {
                map.put("header", bannerService.selectallBanner());
            }
            if (type.equals("wen")) {
                map.put("body", albumService.selectAllAlbum());
            }
            if (type.equals("si")) {
                map.put("body", articleService.selectAllArtical());
            }
        }
        return map;
    }

    @RequestMapping("/wen")
    public Map<String, Object> wen(String id, String uid) {
        Map<String, Object> map = new HashMap<>();

        if (uid == null || id == null) {
            map.put("error", "参数不能为空");
        } else {
            map.put("introduction", albumService.selectWenAlbum());
            map.put("list", chapterService.selectAllChapter());
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.AUserByPhone(phone);
        User user1 = userService.AUserByPassword(password);
        if (phone == null || user == null) {
            map.put("error", "-200");
        } else if (user1 == null) {
            map.put("errormsg", "密码错误");
        } else {
            map = userService.selectAllUser();
        }
        return map;
    }

    @RequestMapping("/regist")
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        User user1 = userService.AUserByPassword(password);
        User user2 = userService.AUserByPhone(phone);
        User user = new User();
        if (user1 != null) {
            map.put("erron", "-200");
        } else if (user2 != null) {
            map.put("erron_msg", "该手机号已经存在");
        } else {
            user.setPhone(phone);
            user.setPassword(password);
            userService.registUser(user);
            map.put("uid", user.getId());
            map.put("phone", user.getPassword());
            map.put("password", user.getPassword());
        }
        return map;
    }

    @RequestMapping("/modify")
    public Map<String, Object> modify(String uid, String gender, String photo, String location,
                                      String description, String nickname, String province, String city, String password) {
        Map<String, Object> map = new HashMap<>();
        User user1 = userService.AUserByPhone(password);
        User user = new User();
        if (uid == null || gender == null || photo == null || location == null || description == null
                || nickname == null || province == null || city == null || password == null) {
            map.put("erron", "-200");
        } else if (user1 != null) {
            map.put("erron_msg", "该手机号已经存在");
        } else {
            user.setId(uid);
            user.setSex(gender);
            user.setPhoto(photo);
            user.setCity(location);
            user.setSign(description);
            user.setName(nickname);
            user.setProvince(province);
            user.setPassword(password);
            userService.updUser(user);
            map.put("password", user.getPassword());
            map.put("farmington", user.getDharma());
            map.put("uid", user.getId());
            map.put("nickname", user.getName());
            map.put("gender", user.getSex());
            map.put("photo", user.getPhoto());
            map.put("location", user.getCity());
            map.put("province", user.getProvince());
            map.put("city", user.getCity());
            map.put("description", user.getSign());
            map.put("phone", user.getPhone());

        }
        return map;
    }

    @RequestMapping("/obtain")
    public Map<String, Object> obtain(String phone) {
        Map<String, Object> map = new HashMap<>();
        if (phone == null) {
            map.put("error", "-200");
        } else {
            map.put("success", "123456");
        }
        return map;
    }

}
