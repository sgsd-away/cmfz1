package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> allUser(Integer page, Integer rows);

    public void updateUser(User user);

    public List<User> selectAll();

    public List<List<Integer>> userCount();

    public Map<String, Object> selectAllUser();

    public void registUser(User user);

    public void updUser(User user);

    public User AUserByPassword(String password);

    public User AUserByPhone(String phone);
}
