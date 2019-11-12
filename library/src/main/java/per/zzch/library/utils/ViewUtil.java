package per.zzch.library.utils;

import android.content.Context;
import android.view.ContextThemeWrapper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/11
 * @desc :
 */
public class ViewUtil {

    /**
     * 将一个context转换为AppCompatActivity
     *
     * @param context context
     * @return AppCompatActivity
     */
    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

}
