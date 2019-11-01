package jiaowuchu

import okhttp3.Cookie

/**
 *单例临时保存Cookie相关
 */
object CookieUtil {
    var id: String = ""
    var cookie: String = ""
    var jwchLoginCookie: String = ""
    var newJwchCookies = mutableMapOf<String, Cookie>()
    var newJwchLastUpdateTime: Long = 0
    var authorizationCookie: String = ""
    var graduateCookies = mutableMapOf<String, Cookie>()
    var lastUpdateTime: Long = 0
    var jwtLastUpdateTime: Long = 0
    var graduateLastUpdateTime: Long = 0
    var emptyRoomViewState: String = ""
    var emptyRoomEventValidation: String = ""
    var yiBanCookie: String = ""
    var libCookie: String = ""
    var experimentCookie: String = ""
    var eCardCookies = mutableMapOf<String, Cookie>()

    fun clear() {
        id = ""
        cookie = ""
        jwchLoginCookie = ""
        newJwchCookies.clear()
        newJwchLastUpdateTime = 0
        authorizationCookie = ""
        graduateCookies.clear()
        jwtLastUpdateTime = 0
        graduateLastUpdateTime = 0
        lastUpdateTime = 0
        yiBanCookie = ""
        libCookie = ""
        experimentCookie = ""
        eCardCookies.clear()
    }
}