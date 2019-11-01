package com.czh.interceptor;

import com.czh.mapper.TokenMapper;
import com.czh.pojo.Token;
import com.czh.common.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenMapper tokenMapper;

    private Set<String> allow_uri = new HashSet<>();
    //提供查询
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {}
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {}
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        allow_uri.add("/jiebei/user/register");
        allow_uri.add("/jiebei/login");
        allow_uri.add("/jiebei/test1/tt");


        //普通路径放行
//        System.out.println(arg0.getRequestURI());
//        if ("/jiebei/user/register".equals(arg0.getRequestURI()) || "/jiebei/login".equals(arg0.getRequestURI()) || "/jiebei/test1/tt".equals(arg0.getRequestURI())) {
//            
//            return true;
//        }
        if(allow_uri.contains(arg0.getRequestURI())){
            return true;
        }

        System.out.println(arg0.getRequestURI());
        //权限路径拦截
        arg1.setCharacterEncoding("UTF-8");
        //PrintWriter resultWriter=arg1.getWriter();
        final String headerToken=arg0.getHeader("XW-Token");


        //判断请求信息
        if(null==headerToken||headerToken.trim().equals("")){
            //resultWriter.write("你没有token,需要登录");
            System.out.println(0);
            return false;
        }
        //解析Token信息
        try {
//            Claims claims = Jwts.parser().setSigningKey("dahao").parseClaimsJws(headerToken).getBody();
//            String tokenUserId=(String)claims.get("userid");
            Claims claims = null;
            String tokenUserId= null;
            try {
                claims = JWTUtil.parse(headerToken);
                tokenUserId = (String)claims.get("uid");
            } catch (Exception e) {
                System.out.println("token解析异常");
            }


            System.out.println(tokenUserId);

            int itokenUserId=Integer.parseInt(tokenUserId);
            //根据客户Token查找数据库Token

            Token login_token = new Token();
            login_token.setUid(itokenUserId);
            List<Token> Token = tokenMapper.select(login_token);
            if(CollectionUtils.isEmpty(Token)){
//                resultWriter.write("该用户不存在，请需要注册");
                System.out.println(1);
                return false;
            }
            Token myToken = Token.get(0);
//            Token myToken=tokenMapper.findByUserId(itokenUserId);

            //数据库没有Token记录
            if(null==myToken) {
//                resultWriter.write("我没有你的token？,需要登录");
                System.out.println(2);
                return false;
            }
            //数据库Token与客户Token比较
            if( !headerToken.equals(myToken.getToken()) ){
//                resultWriter.write("你的token修改过？,需要登录");
                System.out.println(3);
                return false;
            }
            //判断Token过期
            Date tokenDate=(Date)claims.getExpiration();
            int chaoshi=(int)(new Date().getTime()-tokenDate.getTime())/1000;
            if(chaoshi>60*60*24*3){
                //resultWriter.write("你的token过期了？,需要登录");
                System.out.println(4);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            //resultWriter.write("反正token不对,需要登录");
            System.out.println(5);
            return false;
        }
        System.out.println("666");
        //最后才放行
        return true;
    }
}
