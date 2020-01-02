package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @className KindEditorController
 * @Description
 * @Authour 官帅
 * @Date 2019/12/23  11:46
 */
@RestController
@RequestMapping("/kindeditor")
public class KindEditorController {
    @RequestMapping("/upload")
    public Map<String, Object> upload(MultipartFile img, HttpServletRequest request) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        String name = UUID.randomUUID().toString() + img.getOriginalFilename();
        try {
            img.transferTo(new File(request.getSession().getServletContext().getRealPath("image"), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error", 0);
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip DESKTOP-ODTQA95/192.168.253.1
        String ip = localHost.toString().split("/")[1];
        int serverPort = request.getServerPort();//8888
        String contextPath = request.getContextPath();//cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/image/" + name;
        map.put("url", url);
        return map;
    }

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(HttpServletRequest request) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("image");
        File files = new File(realPath);
        String[] names = files.list();//image目录下所有的图片名
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {
            Map<String, Object> file = new HashMap<>();
            file.put("is_dir", false);
            file.put("has_file", false);
            File file1 = new File(realPath, name);
            file.put("filesize", file1.length());
            file.put("dir_path", "");
            file.put("is_photo", true);
            file.put("filetype", FilenameUtils.getExtension(name));
            file.put("filename", name);
            file.put("datetime", "2019-01-17 12:00:00");
            list.add(file);
        }
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip DESKTOP-ODTQA95/192.168.253.1
        String ip = localHost.toString().split("/")[1];
        int serverPort = request.getServerPort();//8888
        String contextPath = request.getContextPath();//cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/image/";
        map.put("current_url", url);
        map.put("total_count", names.length);
        map.put("file_list", list);
        return map;

    }
}
