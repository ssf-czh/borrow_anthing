package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.jiaowuchu.Login;
import com.czh.pojo.User;
import com.czh.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Result> registerUser(User register_user, String schoolpw) throws IOException {

        //注册用户

        int code = Login.login(register_user.getSchoolid(), schoolpw);

        if(code == 1){
            userService.addUser(register_user);

            Result result = new Result(200,"注册成功");
            return ResponseEntity.ok(result);
        }else{
            Result result = new Result(400,"教务处账号或密码错误");
            return ResponseEntity.ok(result);
        }

    }

    @PostMapping("/update")
    public ResponseEntity<Result> updateUser(HttpServletRequest request,User user){
        String token = request.getHeader("XW-Token");
        int uid = JWTUtil.parseToUid(token);

        User findUser = userService.findUserByUid(uid);

        user.setSchoolid(findUser.getSchoolid());
        user.setUid(uid);

        userService.updateUserByUid(user);

        Result result = new Result(200, "修改成功");
        return ResponseEntity.ok(result);
    }

    /**
     * 查找用户信息
     * @param request
     * @return
     */
    @PostMapping("/findUser")
    public ResponseEntity<Result> findUser(HttpServletRequest request){

        String token = request.getHeader("XW-Token");
        System.out.println("token "+token);
        int uid = JWTUtil.parseToUid(token);

        User user = userService.findUserByUid(uid);

        Result result = new Result(200, "查找登入用户资料成功", user);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据uid查找用户 返回非隐私数据
     * @param uid
     * @return
     */
    @PostMapping("/findUserByUid")
    public ResponseEntity<Result> findUserByUid(@RequestParam(required = true) Integer uid){

        User userByUid = userService.findUserByUid(uid);
        userByUid.setPassword(null);
        Result result = new Result(200, "查找他人用户资料成功",userByUid);
        return ResponseEntity.ok(result);
    }



}
