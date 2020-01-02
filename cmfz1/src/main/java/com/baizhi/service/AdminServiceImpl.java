package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @className AdminServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/17  17:25
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public void login(Admin admin, String code, String enCode, HttpServletRequest request) {
        if (!code.equals(enCode)) throw new RuntimeException("验证码错误");
        Admin a = new Admin();
        a.setUsername(admin.getUsername());
        Admin admin1 = adminDAO.selectOne(a);
        if (admin1 == null) throw new RuntimeException("用户名不存在");
        if (!admin1.getPassword().equals(admin.getPassword())) throw new RuntimeException("密码错误");
        request.getSession().setAttribute("loginAdmin", admin1);

    }
}
