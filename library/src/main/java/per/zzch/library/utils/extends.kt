package per.zzch.library.utils

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */

fun String?.sToString(): String {
    if (this == null) {
        return ""
    }
    return this
}

fun String?.sToInt(): Int{
    if (this == null) {
        return 0
    }
    return this.toInt()
}