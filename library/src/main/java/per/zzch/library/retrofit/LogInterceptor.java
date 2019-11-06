package per.zzch.library.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import per.zzch.library.utils.LogUtil;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/28
 * @desc   :日志拦截器，用于网络请求时获取请求信息
 */
public class LogInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    /*
     *  example:
     *
     *  --> POST http://172.10.4.164:8080/login Content-Type: application/json; charset=UTF-8
     *   {"page":"1","size":"10"}
     *  --> END POST
     *  <-- 200  (45ms)
     *   {"msg":"操作成功","data":null,"code":0}
     *  <-- END HTTP
     *
     */

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        Connection connection = chain.connection();

        StringBuilder logMsg = new StringBuilder();

        logMsg.append(".\n.\n--> ")
                .append(request.method())
                .append(" ")
                .append(request.url())
                .append(connection != null ? " " + connection.protocol() : "");
        if (requestBody != null) {
            if (requestBody.contentType() != null) {
                logMsg.append(" Content-Type: ").append(requestBody.contentType());
            }
            logMsg.append("\n  ");
            Buffer buffer = new Buffer();

            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();

            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            // 根据是否为明文，更改输出
            if (isPlaintext(buffer)) {
                logMsg.append(buffer.readString(charset != null ? charset : UTF8));
            } else {
                logMsg.append("(binary ")
                        .append(requestBody.contentLength()).append("-byte body omitted)");
            }
        }
        logMsg.append("\n--> END ").append(request.method()).append("\n\n");


        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logMsg.append("<-- HTTP FAILED: ").append(e);
            logThisMsg(logMsg);
            throw e;
        }

        ResponseBody responseBody = response.body();
        long contentLength = responseBody != null ? responseBody.contentLength() : 0;
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        logMsg.append("<-- ").append(response.code()).append(response.message().isEmpty() ? "" : ' ' + response.message()).append(' ')
                .append(" (").append(tookMs).append("ms").append(")\n  ");

        if (response.body() != null) {
            BufferedSource source = null;
            if (responseBody == null) {
                logMsg.append("null source \n");
                logMsg.append("<-- END HTTP\n.");
                return response;
            }
            source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.getBuffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (!isPlaintext(buffer)) {
                logMsg.append("(binary ").append(buffer.size()).append("-byte body omitted)\n");
                logMsg.append("<-- END HTTP\n.");
                return response;
            }

            if (contentLength != 0) {
                logMsg.append(buffer.clone().readString(charset != null ? charset : UTF8));
            }
        }
        logMsg.append("\n").append("<-- END HTTP\n.");
        logThisMsg(logMsg);
        return response;
    }

    private void logThisMsg(StringBuilder logMsg) {
        logMsg.append("\n");
        LogUtil.d(logMsg.toString());
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    private static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
