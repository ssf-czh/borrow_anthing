package com.czh.Controller;

import com.alibaba.fastjson.JSONObject;
import com.czh.mapper.GoodsMapper;
import com.czh.mapper.TokenMapper;
import com.czh.pojo.Good;
import com.czh.pojo.Token;
import com.czh.pojo.User;
import com.czh.service.GoodsService;
import com.czh.service.TokenService;
import com.czh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/test1")
public class TestController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenMapper tokenMapper;;

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping("/testJson")
    public void testJson(@RequestBody User user){
        System.out.println(user);

    }

    @RequestMapping("/testJson2")
    public void testJson(@RequestBody String password){
        System.out.println(password);
    }

    @RequestMapping("/testJson3")
    public void testJson3(@RequestParam(name = "password",required = true) String password,@RequestParam(name="username")  String username){
        System.out.println(username);
        System.out.println(password);
    }

    @RequestMapping(value = "/testJson3_1",method = {RequestMethod.POST})
    public void testJson3_1(@RequestParam(name = "password",required = true) String password,@RequestParam(name="username")  String username){
        System.out.println(username);
        System.out.println(password);
    }

    @RequestMapping(value = "/testJson4",method = {RequestMethod.POST})
    public void testJson4(User user){
        System.out.println(user);
    }


    @RequestMapping(value = "/testJson5")
    public void testJson5(User user){
        System.out.println("hello");
    }

    @RequestMapping(value = "/testFastJson")
    public void testJson5(@RequestBody JSONObject jsonObject){

        String username = jsonObject.getString("username");
        System.out.println(username);

//        必须加大括号
//
//        {
//            "username":{
//            "name":"czh"
//        }
//        }
        System.out.println(jsonObject.getJSONObject("username"));
        System.out.println(jsonObject.getJSONObject("username").getString("name"));
        JSONObject username1 = jsonObject.getJSONObject("username").getJSONObject("name");
        System.out.println(username1);
//        String Username1 = (String)JSONObject.toJavaObject(username1,String.class);
//        System.out.println(Username1);

//        Integer age = jsonObject.getInteger("age");
//        System.out.println(age);
//
//        JSONObject age1 = jsonObject.getJSONObject("age");
//        Integer Age1 = (Integer)JSONObject.toJavaObject(age1,Integer.class);
//        System.out.println(Age1);


//        Task task = (Task)JSONObject.toJavaObject(task_json,Task.class);
//        System.out.println(username);
//        String Username = (String)JSONObject.toJavaObject(username,String.class);
//        System.out.println(Username);
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void testSql() throws Exception {
        Token insert_token = new Token();
        insert_token.setUid(1);
        insert_token.setBuildtime(123456);
//        token.setTid(11);
        insert_token.setToken("dafsadfsadfdsafdsa");
        System.out.println(insert_token);
//        tokenService.addToken(token);
//        tokenMapper.insert(token);
        tokenMapper.insertSelective(insert_token);
        System.out.println(insert_token);


//        User user = new User();
//        user.setSchoolid("12311");
//        user.setPassword("222");
//        System.out.println(user);
//        userService.addUser(user);
//        System.out.println(user);

    }

    @RequestMapping("/tt")
    private User ttest(HttpServletRequest request){
        User user = new User();
        user.setName("ccccc");
        return user;
    }


}
