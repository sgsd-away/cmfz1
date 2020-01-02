package com.baizhi;

import com.baizhi.dao.UserDAO;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @className TestUser
 * @Description
 * @Authour 官帅
 * @Date 2019/12/25  16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void test2() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-71377dac8fd940fca1ec0cacf672d73c");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
