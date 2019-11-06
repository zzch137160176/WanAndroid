package per.zzch.library.utils;

import android.content.pm.PackageManager;

import per.zzch.library.A;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/29
 * @desc   :
 */
public class AppInfoUtil {

    /**
     * 获取包名
     * @return packageName
     */
    public static String getPackageName() {
        return A.instance.getPackageName();
    }

    /**
     * 获取版本名
     * @return versionName
     */
    public static String getVersionName() {
        String pkName = getPackageName();
        try {
            return A.instance.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本号
     * @return versionCode
     */
    public static int getVersionCode() {
        String pkName = getPackageName();
        try {
            return A.instance.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
