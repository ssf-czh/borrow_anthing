package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.User;
import com.czh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Result> registerUser(User register_user){

        //注册用户
        userService.addUser(register_user);

        Result result = new Result(200,"注册成功");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> updateUser(HttpServletRequest request,User user){
        String token = request.getHeader("XW-Token");
        int uid = JWTUtil.parseToUid(token);

        User findUser = userService.findUserByUid(uid);

        user.setUid(uid);
        user.setSchoolid(findUser.getSchoolid());
        user.setPassword(findUser.getPassword());
        System.out.println(user);
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

        Result result = new Result(200, "查找用户成功", user);
        return ResponseEntity.ok(result);
    }


}
