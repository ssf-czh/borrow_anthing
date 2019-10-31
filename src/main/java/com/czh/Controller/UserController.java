package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.RegisterResult;
import com.czh.pojo.User;
import com.czh.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResult> registerUser(User register_user){
        RegisterResult registerResult = new RegisterResult();
        //注册用户
        userService.addUser(register_user);
        registerResult.setFlag(true);
        registerResult.setMsg("注册成功");

        return ResponseEntity.ok(registerResult);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(HttpServletRequest request,User user){
        String token = request.getHeader("XW-Token");
        int uid = JWTUtil.parseToUid(token);

        User findUser = userService.findUserByUid(uid);

        user.setUid(uid);
        user.setSchoolid(findUser.getSchoolid());
        user.setPassword(findUser.getPassword());
        System.out.println(user);
        userService.updateUserByUid(user);
        return ResponseEntity.status(200).build();
    }

    /**
     * 查找用户信息
     * @param request
     * @return
     */
    @PostMapping("/findUser")
    public ResponseEntity<User> findUser(HttpServletRequest request){

        String token = request.getHeader("XW-Token");
        System.out.println("token "+token);
        int uid = JWTUtil.parseToUid(token);
        System.out.println(uid);
        User user = userService.findUserByUid(uid);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }


}
