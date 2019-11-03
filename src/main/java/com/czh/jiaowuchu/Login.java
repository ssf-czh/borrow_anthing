package com.czh.jiaowuchu;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.Charset;

public class Login {
    public static int NET_ERROR = -1;
    public static int LOGIN_ERROR = 0;
    public static int LOGIN_SUCCESS = 1;
    public static int LOGIN_PWD_ERROR = 2;
    public static int LOGIN_VERIFY_ERROR = 3;
    public static int LOGIN_ERROR_MANY = 4;
    public static int LOGIN_NOT_EXISTS_USER = 5;
    public static int LOGIN_PWD_WEAK = 6;
    public static int login(String user,String pass) throws IOException {
        Response<ResponseBody> result = RetrofitHelper.getJwchApi().loginStudent(user, pass, "").execute();
        if (result.isSuccessful()) {
            String body;
            ResponseBody responseBody = result.body();
            if(responseBody!=null) {
                body = new String(responseBody.bytes(), Charset.forName("gb2312"));
            } else {
                body = new String(new byte[]{},Charset.forName("gb2312"));
            }
            if(body.contains("left.aspx")) {
                HttpUrl url = result.raw().request().url();
                CookieUtil.id = url.toString().split("id=")[1];
                CookieUtil.lastUpdateTime = System.currentTimeMillis();
                return LOGIN_SUCCESS;
            }
            int code;
            if(body.contains("不存在该用户")) {
                code = LOGIN_NOT_EXISTS_USER;
            } else if(body.contains("验证码验证失败")) {
                code = LOGIN_VERIFY_ERROR;
            } else if(body.contains("密码错误")) {
                code = LOGIN_PWD_ERROR;
            } else if(body.contains("原密码较弱")) {
                code = LOGIN_PWD_WEAK;
            } else {
                code = LOGIN_ERROR;
            }
            return code;
        } else {
            return  NET_ERROR;
        }
    }
}
