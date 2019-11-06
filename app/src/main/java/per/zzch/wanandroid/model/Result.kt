package per.zzch.wanandroid.model

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/5
 * @desc   :
 */

data class Result<T>(
    val data: T? = null,
    val errorCode: Int = 0,
    val errorMsg: String? = null
)