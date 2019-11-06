package per.zzch.library.retrofit;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/28
 * @desc   :提供返回结果处理的封装类
 */
public class ResponseTransformer {

    private static final String SUCCESS = "0";

    /**
     * 处理错误
     */
    public static <T> SingleTransformer<Result<T>, Result<T>> handleException() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }

    /**
     * 状态码为 SUCCESS 时，表示成功
     */
    private static boolean success(String code) {
        return SUCCESS.equals(code);
    }

    /**
     * 处理本地异常
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, SingleSource<Result<T>>> {

        @Override
        public SingleSource<Result<T>> apply(Throwable throwable) throws Exception {
            return Single.error(ExceptionHandle.handleException(throwable));
        }
    }

    /**
     * 处理服务器返回的异常
     */
    private static class ResponseFunction<T> implements Function<Result<T>, SingleSource<Result<T>>> {

        @Override
        public SingleSource<Result<T>> apply(Result<T> result) throws Exception {
            String code = result.getCode();
            if (success(code)) {
                return Single.just(result);
            } else {
                return Single.error(new ApiException(code, result.getMsg()));
            }
        }
    }

}
