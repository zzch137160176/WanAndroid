package per.zzch.library.retrofit

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import per.zzch.library.utils.LogUtil

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/4
 * @desc   :
 */

fun <T> Single<T>.sync(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

@SuppressLint("CheckResult")
fun <T> Single<T>.execute(success: (T) -> Unit, error: (Throwable) -> Unit) {
    this.subscribe({
        success.invoke(it)
    },{
        LogUtil.e(it.message)
        error.invoke(it)
    })
}

fun <T> Single<T>.executeWithDispose(success: (T) -> Unit, error: (Throwable) -> Unit): Disposable {
    return this.subscribe({
        success.invoke(it)
    },{
        LogUtil.e(it.message)
        error.invoke(it)
    })
}