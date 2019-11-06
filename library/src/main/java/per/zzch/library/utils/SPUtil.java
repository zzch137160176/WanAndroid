package per.zzch.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import per.zzch.library.A;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/29
 * @desc   :
 */
public class SPUtil {

    private static String SP_NAME = "sp_" + AppInfoUtil.getPackageName();

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String IS_SAVE_PASSWORD = "is_save_password";

    public static void set(String key, Object value) {
        SharedPreferences sharedPreferences = A.instance.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.apply();
    }

    public static Object get(String key, Object defaultValue) {
        SharedPreferences sharedPreferences = A.instance.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else {
            return null;
        }
    }
}
