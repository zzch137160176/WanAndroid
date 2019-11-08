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

fun String?.sToInt(): Int {
    if (this == null) {
        return 0
    }
    return this.toInt()
}

/**
 * 检验整型变量是否为空或为0
 * @receiver Int?
 * @return Boolean
 */
fun Int?.hasValue(): Boolean {
    if (this == null) {
        return false
    }
    if (this == 0) {
        return false
    }
    return true
}