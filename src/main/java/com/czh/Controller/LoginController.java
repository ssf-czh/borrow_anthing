package com.czh.Controller;

import com.czh.common.vo.Result;
import com.czh.common.vo.TokenResult;
import com.czh.pojo.Token;
import com.czh.pojo.User;
import com.czh.service.TokenService;
import com.czh.service.UserService;
import com.czh.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Result> login(User loginUser) {
        //创建返回信息对象
        System.out.println("哈哈");

        System.out.println(loginUser);

        //通过用户名密码查找用户
        User lg_user = userService.findUserByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());

        //根据数据库的用户信息查询Token
        Token token = tokenService.findByUserId(lg_user.getUid());
        //为生成Token准备
        String TokenStr = "";
        Date date = new Date();
        int nowtime = (int) (date.getTime() / 1000);
        //生成Token
        TokenStr = JWTUtil.creatToken(lg_user, date);
        if (null == token) {
            //第一次登陆
            token = new Token();
            token.setToken(TokenStr);
            token.setBuildtime(nowtime);
            token.setUid(lg_user.getUid());
            tokenService.addToken(token);
        }else{
            //登陆就更新Token信息
            TokenStr = JWTUtil.creatToken(lg_user, date);
            token.setToken(TokenStr);
            token.setBuildtime(nowtime);
            tokenService.updataToken(token);
        }
        //返回Token信息给客户端
        Result result = new Result(200,"登入成功",TokenStr);
        return ResponseEntity.ok(result);
        }

}
