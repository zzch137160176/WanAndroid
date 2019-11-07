package per.zzch.library.utils

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */
open class BasePage<T>(
    var list: List<T> = ArrayList(),
    var totalSize: Int = 0,
    var totalPage: Int = 0,
    var page: Int  = 0
)