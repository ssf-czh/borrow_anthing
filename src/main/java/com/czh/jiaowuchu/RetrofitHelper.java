package com.czh.jiaowuchu;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RetrofitHelper {


    private static JwchApiService jwchApiServiceInstance;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static JwchApiService getJwchApi() {
        if (jwchApiServiceInstance == null) {
            jwchApiServiceInstance = createApi(JWCH_BASE_URL, client.newBuilder().cookieJar(new CookieJar() {
                List<Cookie> cookieList = new ArrayList<>();
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    if (url.host().contains("fzu.edu.cn")) {
                        cookieList.addAll(cookies);
                        for (Cookie cookie : cookies) {
                            if (cookie.name().equals("iPlanetDirectoryPro")) {
                                CookieUtil.authorizationCookie = cookie.toString();
                            }
                        }
                    } else if (url.host().contains("59.77.226.32")) {
                        cookies.forEach(it -> {
                            if (it.name().startsWith("ASPSESSION")) {
                                CookieUtil.jwchLoginCookie = it.toString();
                            }
                        });
                    } else {
                        for (Cookie cookie : cookies) {
                            if (cookie.name().equals("ASP.NET_SessionId")) {
                                CookieUtil.cookie = cookie.toString();
                            }
                        }
                    }
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    if (url.host().contains("fzu.edu.cn")) {
                        String cookieStr = CookieUtil.authorizationCookie;
                        if (!cookieStr.isEmpty()) {
                            Cookie cookie = Cookie.parse(url, cookieStr);
                            cookieList.add(cookie);
                        }
                        return cookieList;
                    } else {
                        if (!cookieList.isEmpty()) {
                            cookieList.clear();
                        }
                        if (url.host().contains("59.77.226.32")) {
                            String cookieStr = CookieUtil.jwchLoginCookie;
                            if (!cookieStr.isEmpty()) {
                                Cookie cookie = Cookie.parse(url, cookieStr);
                                List<Cookie> cookies = new ArrayList<>();
                                cookies.add(cookie);
                                return cookies;
                            }
                        } else {
                            String cookieStr = CookieUtil.cookie;
                            if (!cookieStr.isEmpty()) {
                                Cookie cookie = Cookie.parse(url, cookieStr);
                                List<Cookie> cookies = new ArrayList<>();
                                cookies.add(cookie);
                                return cookies;
                            }
                        }
                        return new ArrayList();
                    }
                }
            }).build());
        }
        return jwchApiServiceInstance;
    }

    private static JwchApiService createApi(String url, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .build();
        return retrofit.create(JwchApiService.class);
    }

    private static String JWCH_BASE_URL = "http://59.77.226.35";
}
