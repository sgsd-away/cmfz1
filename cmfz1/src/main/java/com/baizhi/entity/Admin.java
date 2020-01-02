package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @className Admin
 * @Description
 * @Authour 官帅
 * @Date 2019/12/17  14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;
    private String nickname;
}
