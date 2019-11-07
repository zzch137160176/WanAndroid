package per.zzch.wanandroid.model

import per.zzch.library.utils.BasePage

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */
data class Page<T>(
    val curPage: Int = 0,
    val datas: List<T>? = null,
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
): BasePage<T>() {

    fun refreshPage() {
        list = this.datas ?: ArrayList()
        totalPage = this.pageCount
        totalSize = this.total
        page = this.curPage
    }
}