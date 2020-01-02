package com.baizhi;

import com.baizhi.dao.AdminDAO;
import com.baizhi.dao.BannerDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Cmfz1ApplicationTests {
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    BannerDAO bannerDAO;
    @Autowired
    ChapterDAO chapterDAO;

    @Test
    public void test1() {
        Admin a = new Admin();
        a.setUsername("admin");
        a.setPassword("admin");
        Admin admin = adminDAO.selectOne(a);
        System.out.println(admin);
    }

    @Test
    public void test2() {
        Admin a = new Admin();
        a.setUsername("admin");
        a.setPassword("admin");
        List<Admin> admins = adminDAO.select(a);
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    @Test
    public void test3() {
        List<Admin> admins = adminDAO.selectAll();
        for (Admin admin : admins) {
            System.out.println("===============" + admin + "=================");
        }
    }

    @Test
    public void test4() {
        Admin a = new Admin();
        a.setPassword("12345");
        List<Admin> admins = adminDAO.select(a);
        for (Admin admin : admins) {
            System.out.println("===============" + admin + "=================");
        }
    }

    @Test
    public void test5() {
        Admin a = new Admin();
        a.setId("4");
        a.setUsername("admin1");
        a.setPassword("12345");
        int i = adminDAO.insert(a);
        System.out.println(i);
    }

    @Test
    public void test6() {
        Admin a = new Admin();
        a.setId("5");
        a.setUsername("admin1");
        //a.setPassword("12345");
        int i = adminDAO.insertSelective(a);
        System.out.println(i);
    }

    @Test
    public void test7() {
        //insert是插入所有的 insertSelective是插入所有的非空值
        Admin a = new Admin();
        a.setId("5");
        a.setUsername("admin2");
        //a.setPassword("12345");
        int i = adminDAO.updateByPrimaryKeySelective(a);
        System.out.println(i);
    }

    @Test
    public void test8() {
        //insert是插入所有的 insertSelective是插入所有的非空值
        Admin a = new Admin();
        a.setId("4");
        a.setUsername("admin2");
        //a.setPassword("12345");
        int i = adminDAO.updateByPrimaryKeySelective(a);
        System.out.println(i);
    }

    @Test
    public void test9() {
        Admin a = new Admin();
        a.setId("4");
        adminDAO.deleteByPrimaryKey(a);
    }

    @Test
    public void test10() {
        Admin a = new Admin();
        a.setPassword("12345");
        adminDAO.delete(a);
    }

    @Test
    public void test11() {
        Example e = new Example(Admin.class);
        e.createCriteria().andEqualTo("username", "admin2");
        adminDAO.deleteByExample(e);
    }

    @Test
    public void test12() {
        Admin a = new Admin();
        int i = adminDAO.selectCount(a);
        System.out.println(i);
    }

    @Test
    public void test13() {
        Integer page = 1;
        Integer rows = 3;
        Banner banner = new Banner();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Banner> banners = bannerDAO.selectByRowBounds(banner, rowBounds);
        System.out.println(banners);
    }

    @Test
    public void test14() {
        Banner banner = new Banner();
        List<Banner> banners = bannerDAO.select(banner);
        System.out.println(banners);
    }

    @Test
    public void test15() {
        Example e = new Example(Chapter.class);
        e.createCriteria().andEqualTo("album_id", "2");
        RowBounds rowBounds = new RowBounds(1, 3);
        List<Chapter> chapters = chapterDAO.selectByExampleAndRowBounds(e, rowBounds);
        System.out.println(chapters);
    }

    @Test
    public void test16() {
        /*Example e = new Example(Chapter.class);
        e.createCriteria().andEqualTo("album_id","2");
        RowBounds rowBounds = new RowBounds(1, 3);
        List<Chapter> chapters = chapterDAO.selectByExampleAndRowBounds(e, rowBounds);*/
        Example e = new Example(Chapter.class);
        e.createCriteria().andEqualTo("album_id", "9d4e6b1f-5f99-4daf-8b53-9d7e16bc74fb");
        List<Chapter> chapters = chapterDAO.selectByExample(e);
        int size = chapters.size();
        System.out.println("*********************" + size + "*********************");
    }
}
