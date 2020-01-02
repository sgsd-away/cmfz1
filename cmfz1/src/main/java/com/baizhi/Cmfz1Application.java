package com.baizhi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
@org.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
public class Cmfz1Application {

    public static void main(String[] args) {
        SpringApplication.run(Cmfz1Application.class, args);
    }

}
