package jiaowuchu

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface JwchApiService {
    @POST("http://59.77.226.32/logincheck.asp")
    @FormUrlEncoded
    @Headers("Referer:http://jwch.fzu.edu.cn/", "Connection:keep-alive")
    fun loginStudent(@Field("muser") user: String,
                             @Field("passwd") pass: String,
                             @Field("Verifycode") verifyCode: String
    ): Call<ResponseBody>
}