package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @className TestPoi
 * @Description
 * @Authour 官帅
 * @Date 2019/12/24  11:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPoi {
    @Autowired
    UserDAO userDAO;

    @Test
    public void test1() {
        //1.先创建Excel表格
        //2.创建工作表
        //3.创建Row行
        //4.创建单元格
        //5.像单元格添加数据
        //6.poi导出
    }

    @Test
    public void test2() throws IOException {
        List<User> users = userDAO.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("佛教弟子", "大和尚"), User.class, users);
        workbook.write(new FileOutputStream("F:/测试.xls"));
    }
}
