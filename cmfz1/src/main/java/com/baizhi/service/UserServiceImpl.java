package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @className UserServiceImpl
 * @Description
 * @Authour 官帅
 * @Date 2019/12/20  16:11
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Map<String, Object> allUser(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDAO.selectByRowBounds(user, rowBounds);
        int count = userDAO.selectCount(user);
        map.put("rows", users);
        map.put("page", page);
        map.put("records", count);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        return map;
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> selectAll() {
        List<User> users = userDAO.selectAll();
        return users;
    }

    @Override
    public List<List<Integer>> userCount() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> listb = new ArrayList<>();
        List<Integer> listg = new ArrayList<>();
        Integer boy1 = userDAO.selectOneWeekBoy();
        Integer girl1 = userDAO.selectOneWeekGirl();
        Integer boy2 = userDAO.selectTwoWeekBoy();
        Integer girl2 = userDAO.selectTwoWeekGirl();
        Integer boy3 = userDAO.selectThreeWeekBoy();
        Integer girl3 = userDAO.selectThreeWeekGirl();
        listb.add(boy1);
        listb.add(boy2);
        listb.add(boy3);
        listg.add(girl1);
        listg.add(girl2);
        listg.add(girl3);
        list.add(listb);
        list.add(listg);
        return list;

    }

    @Override
    public Map<String, Object> selectAllUser() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userDAO.selectAll();
        for (User user : users) {
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

    @Override
    public void registUser(User user) {
        user.setId(UUID.randomUUID().toString());
        userDAO.insertSelective(user);
    }

    @Override
    public void updUser(User user) {
        userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public User AUserByPassword(String password) {
        User user = new User();
        user.setPassword(password);
        User user1 = userDAO.selectOneByExample(user);
        return user1;
    }

    @Override
    public User AUserByPhone(String phone) {
        User user = new User();
        user.setPassword(phone);
        User user1 = userDAO.selectOneByExample(user);
        return user1;
    }


}
