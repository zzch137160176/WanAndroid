package per.zzch.wanandroid.model

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/8
 * @desc   :
 */
data class Navigation<T>(
    val articles: List<T>? = null,
    val cid: Int? = 0,
    val name: String? = null
)