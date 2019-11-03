package com.czh.service;

import com.czh.pojo.User;

public interface UserService {

//    根据账号密码来查询用户
    User findUserByUsernameAndPassword(String username, String password);
//    注册用户
    void addUser(User register_user);
//     根据uid更新用户信息
    void updateUserByUid(User uid);
//      根据uid查找用户
    User findUserByUid(int uid);


}
