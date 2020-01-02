package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className AdminController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/17  16:37
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/getCode")
    public String getCode(HttpSession session, HttpServletResponse response) {
        String code = SecurityCode.getSecurityCode();//得到验证码
        session.setAttribute("code", code);//将验证码存入session作用域
        BufferedImage img = SecurityImage.createImage(code);//创建图片
        try {
            ServletOutputStream stream = response.getOutputStream();
            ImageIO.write(img, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/login")

    public @ResponseBody
    Map<String, Object> login(HttpServletRequest request, Admin admin, String enCode) {
        System.out.println(admin + ">>>>>>" + enCode);
        Map<String, Object> map = new HashMap<>();
        String code = (String) request.getSession().getAttribute("code");
        try {
            adminService.login(admin, code, enCode, request);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
