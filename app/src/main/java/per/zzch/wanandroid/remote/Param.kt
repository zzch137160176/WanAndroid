package per.zzch.wanandroid.remote

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/11
 * @desc   :
 */

data class LoginParam (
    val username: String? = "",
    val password: String? = ""
)

data class RegisterParam(
    val username: String? = "",
    val password: String? = "",
    val repassword: String? = ""
)