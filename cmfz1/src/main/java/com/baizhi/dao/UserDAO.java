package com.baizhi.dao;

import com.baizhi.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @className UserDAO
 * @Description
 * @Authour 官帅
 * @Date 2019/12/20  15:55
 */
@Repository
public interface UserDAO extends Mapper<User> {
    public Integer selectOneWeekBoy();

    public Integer selectOneWeekGirl();

    public Integer selectTwoWeekBoy();

    public Integer selectTwoWeekGirl();

    public Integer selectThreeWeekBoy();

    public Integer selectThreeWeekGirl();
}
