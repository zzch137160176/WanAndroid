package per.zzch.wanandroid.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import per.zzch.library.base.BaseActivity
import per.zzch.library.utils.ToastUtil
import per.zzch.wanandroid.R

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/11
 * @desc   :
 */
class LoginActivity: BaseActivity(R.layout.activity_login) {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initEventAndData() {

    }

    override fun onCreate() {
        initEventAndData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }
}