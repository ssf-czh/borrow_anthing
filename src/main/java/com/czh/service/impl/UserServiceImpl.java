package com.czh.service.impl;

import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.mapper.UserMapper;
import com.czh.pojo.User;
import com.czh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据学号和密码查找用户
     * @param schoolid
     * @param password
     * @return
     */
    @Override
    public User findUser(String schoolid, String password) {
//        return userMapper.findUser(schoolid,password);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("schoolid",schoolid).andEqualTo("password",password);
        User user = userMapper.selectOneByExample(example);
        if(user == null){
            throw new JieBeiException(ExceptionEnum.USER_NOT_ERROR);
        }
        return user;


    }

    /**
     * 注册用户
     * @param register_user
     */
    @Override
    @Transactional
    public void addUser(User register_user) {
        int insert = userMapper.insert(register_user);
        if (insert != 1) {
            throw new JieBeiException(ExceptionEnum.ADD_USER_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateUserByUid(User user) {
        int count = userMapper.updateByPrimaryKey(user);
        if(count!=1){
            throw new JieBeiException(ExceptionEnum.USER_UPDATE_ERROR);
        }
    }

    @Override
    public User findUserByUid(int uid) {
        User uid_user = new User();
        uid_user.setUid(uid);
        User user = userMapper.selectOne(uid_user);
        if(null == user){
            throw new JieBeiException(ExceptionEnum.USER_NOT_ERROR);
        }
        return user;
    }

}
