package com.czh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestAnything {
    @Test
    public void test1(){
        Set<String> set = new HashSet<>();
        set.add("123");
        set.add("234");
        System.out.println(set.contains("678"));


    }
}
