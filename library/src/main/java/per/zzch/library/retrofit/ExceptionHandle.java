package per.zzch.library.retrofit;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.text.ParseException;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/9/28
 * @desc :
 */
class ExceptionHandle {

    /**
     * 根据异常各类处理本地异常
     *
     * @param e Throwable
     * @return ApiException
     */
    static ApiException handleException(Throwable e) {
        ApiException exception;
        if (e instanceof JsonParseException ||
                e instanceof JSONException ||
                e instanceof ParseException) {
            exception = new ApiException("1001", "JSON解析错误");
        } else if (e instanceof SocketTimeoutException) {
            exception = new ApiException("1002", "网络连接超时");
        } else {
            exception = new ApiException("1009", "网络异常,请稍后再试");
        }
        return exception;
    }

}
