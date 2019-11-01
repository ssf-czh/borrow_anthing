package jiaowuchu

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private var jwchApiServiceInstance: JwchApiService? = null
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }
    fun getJwchApi(): JwchApiService {
        if (jwchApiServiceInstance == null) {
            val client = client.newBuilder().cookieJar(object : CookieJar {
                val cookieList = mutableListOf<Cookie>()
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    if (url.host().contains("fzu.edu.cn")) {
                        cookieList.addAll(cookies)
                        for (cookie in cookies) {
                            if (cookie.name() == "iPlanetDirectoryPro") {
                                CookieUtil.authorizationCookie = cookie.toString()
                            }
                        }
                    } else if (url.host().contains("59.77.226.32")) {
                        cookies.forEach {
                            if (it.name().startsWith("ASPSESSION")) {
                                CookieUtil.jwchLoginCookie = it.toString()
                            }
                        }
                    } else {
                        for (cookie in cookies) {
                            if (cookie.name() == "ASP.NET_SessionId") {
                                CookieUtil.cookie = cookie.toString()
                            }
                        }
                    }
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    if (url.host().contains("fzu.edu.cn")) {
                        val cookieStr = CookieUtil.authorizationCookie
                        if (cookieStr.isNotEmpty()) {
                            val cookie = Cookie.parse(url, cookieStr)
                            cookieList.add(cookie!!)
                        }
                        return cookieList
                    } else {
                        if (cookieList.isNotEmpty()) {
                            cookieList.clear()
                        }
                        if (url.host().contains("59.77.226.32")) {
                            val cookieStr = CookieUtil.jwchLoginCookie
                            if (cookieStr.isNotEmpty()) {
                                val cookie = Cookie.parse(url, cookieStr)
                                return arrayListOf(cookie!!)
                            }
                        } else {
                            val cookieStr = CookieUtil.cookie
                            if (cookieStr.isNotEmpty()) {
                                val cookie = Cookie.parse(url, cookieStr)
                                return arrayListOf(cookie!!)
                            }
                        }
                        return ArrayList()
                    }
                }
            })
                    .build()
            jwchApiServiceInstance = createApi(JWCH_BASE_URL, client)
        }
        return jwchApiServiceInstance!!
    }

    private fun createApi(url: String, client: OkHttpClient): JwchApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .build()
        return retrofit.create(JwchApiService::class.java)
    }
    const val JWCH_BASE_URL = "http://59.77.226.35"
}
