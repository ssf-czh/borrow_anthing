package jiaowuchu

fun login(user: String, pass: String):Int {
        var captcha = ""
        val result = RetrofitHelper.getJwchApi().loginStudent(user, pass, captcha).execute()
        if (result.isSuccessful) {
            val body = String(result.body()?.bytes() ?: byteArrayOf(), charset("gb2312"))
            if (body.contains("left.aspx")) {
                val url = result.raw().request().url()
                CookieUtil.id = url.toString().split("id=")[1]
                CookieUtil.lastUpdateTime = System.currentTimeMillis()
               return LoginResultCode.LOGIN_SUCCESS
            }
            return  when {
                body.contains("不存在该用户") -> LoginResultCode.LOGIN_NOT_EXISTS_USER
                body.contains("验证码验证失败") -> LoginResultCode.LOGIN_VERIFY_ERROR
                body.contains("密码错误") -> LoginResultCode.LOGIN_PWD_ERROR
                body.contains("原密码较弱") -> LoginResultCode.LOGIN_PWD_WEAK
                else -> LoginResultCode.LOGIN_ERROR
            }
        } else {
            return LoginResultCode.NET_ERROR
        }
}

object LoginResultCode {
    const val NET_ERROR = -1
    const val LOGIN_ERROR = 0
    const val LOGIN_SUCCESS = 1
    const val LOGIN_PWD_ERROR = 2
    const val LOGIN_VERIFY_ERROR = 3
    const val LOGIN_ERROR_MANY = 4
    const val LOGIN_NOT_EXISTS_USER = 5
    const val LOGIN_PWD_WEAK = 6
}