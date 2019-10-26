package com.czh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.czh.mapper")
public class JieBeiSpringApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(JieBeiSpringApplication.class);
    }
}
