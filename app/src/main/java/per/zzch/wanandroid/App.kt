package per.zzch.wanandroid

import per.zzch.library.A

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */
class App: A() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}