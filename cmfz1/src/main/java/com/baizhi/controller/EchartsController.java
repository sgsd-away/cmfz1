package com.baizhi.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className EchartsController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/25  16:39
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public List<List<Integer>> getAll() {
        List<List<Integer>> lists = userService.userCount();
        String s = JSON.toJSON(lists).toString();
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-71377dac8fd940fca1ec0cacf672d73c");
        goEasy.publish("aaa", s);
        return lists;

    }
}
