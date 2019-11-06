package per.zzch.library.utils.helper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import per.zzch.library.retrofit.LogInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/27
 * @desc   :
 */
public class RetrofitHelper {

    private static final int TIMEOUT = 5;

    private static OkHttpClient client = new OkHttpClient.Builder()
            // 自定义拦截器
            .addInterceptor(new LogInterceptor())
            // 超时
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            // 失败重连
            .retryOnConnectionFailure(true)
            .build();

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

}
