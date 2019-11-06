package per.zzch.library.utils;

import android.widget.Toast;

import per.zzch.library.A;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/27
 * @desc   :Toast工具类
 */
public class ToastUtil {

    public static void show(CharSequence msg) {
        Toast.makeText(A.instance.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(CharSequence msg) {
        Toast.makeText(A.instance.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
