package com.czh.Controller;

import com.alibaba.fastjson.JSONObject;
import com.czh.pojo.Good;
import com.czh.pojo.User;
import com.czh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private GoodsService goodsService;

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

    @RequestMapping(value = "/testSql")
    public void testSql(){
        Good good = new Good();
        good.setGid(1);
        good = goodsService.findGoodByGid(good);
        System.out.println(good);
    }


}
