package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className UserController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/20  17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/allUser")
    public Map<String, Object> allUser(Integer page, Integer rows) {
        Map<String, Object> map = userService.allUser(page, rows);
        return map;
    }

    @RequestMapping("/change")
    public Map<String, Object> change(String oper, User user) {
        Map<String, Object> map = new HashMap<>();
        //System.out.println("==================="+oper+"======================");
        if (oper.equals("edit")) {
            userService.updateUser(user);
        }
        map.put("status", true);
        return map;
    }

    @RequestMapping("/daochu")
    public void daochu(HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        List<User> users = userService.selectAll();
        //补全路径
        String realPath = session.getServletContext().getRealPath("image");
        for (User user : users) {
            user.setPhoto(realPath + "//" + user.getPhoto());
            System.out.println(user);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("佛教弟子", "大和尚"), User.class, users);
        //设置响应头
        String encode = URLEncoder.encode("182班.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());

    }
}
