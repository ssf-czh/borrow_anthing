package com.czh.jiaowuchu;

import okhttp3.Cookie;

import java.util.HashMap;

public class CookieUtil {

    public static String id = "";
    public static String cookie = "";
    public static String jwchLoginCookie = "";
    public static HashMap newJwchCookies = new HashMap<String, Cookie>();
    public static Long newJwchLastUpdateTime = 0L;
    public static String authorizationCookie = "";
    public static HashMap graduateCookies = new HashMap<String, Cookie>();
    public static Long lastUpdateTime = 0L;
    public static Long jwtLastUpdateTime = 0L;
    public static Long graduateLastUpdateTime = 0L;
    public static String emptyRoomViewState = "";
    public static String emptyRoomEventValidation = "";
    public static String yiBanCookie = "";
    public static String libCookie = "";
    public static String experimentCookie = "";
    public static HashMap eCardCookies = new HashMap<String, Cookie>();
}
