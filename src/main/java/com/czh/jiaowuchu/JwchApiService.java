package com.czh.jiaowuchu;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JwchApiService {
    @POST("http://59.77.226.32/logincheck.asp")
    @FormUrlEncoded
    @Headers(value = {"Referer:http://jwch.fzu.edu.cn/", "Connection:keep-alive"})
    Call<ResponseBody> loginStudent(@Field("muser") String user,
                                    @Field("passwd") String pass,
                                    @Field("Verifycode") String code
    );
}
