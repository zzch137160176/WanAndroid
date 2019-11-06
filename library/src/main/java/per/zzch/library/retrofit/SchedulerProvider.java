package per.zzch.library.retrofit;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/28
 * @desc   :提供线程调度的封装类
 */
public class SchedulerProvider {

    /**
     * Single 线程切换
     */
    public static <T> SingleTransformer<T, T> singleIo2Main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Observble 线程切换
     */
    public static <T> ObservableTransformer<T, T> observableIo2Main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
