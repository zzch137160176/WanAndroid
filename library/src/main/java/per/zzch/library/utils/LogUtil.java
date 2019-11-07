package per.zzch.library.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/27
 * @desc   :
 */
public class LogUtil {

    private static boolean showLog = true;
    private static String tag = "log`";

    public static void init(boolean isShow, String newTag) {
        showLog = isShow;
        if (newTag == null || newTag.isEmpty()) {
            return;
        }
        tag = newTag;
    }
    
    public static void d(String msg) {
        if (!showLog) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void i(String msg) {
        if (!showLog) {
            return;
        }
        Log.i(tag, msg);
    }

    public static <T> void i(List<T> list) {
        if (!showLog) {
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(list);
        i("list:");
        i(json, true);
    }

    public static void i(String json, boolean isJson) {
        if (!showLog) {
            return;
        }
        if (!isJson) {
            return;
        }
        String message;

        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(4);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(4);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        printLine(true);
        Log.i(tag, message);
        printLine(false);
    }

    public static void e(String msg) {
        if (!showLog) {
            return;
        }
        Log.e(tag, msg);
    }

    private static void printLine(boolean isTop) {
        if (isTop) {
            Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
