package per.zzch.library.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/7
 * @desc :
 */
public class DateUtil {

    private static final long SEC_MIL = 1000;
    private static final long MIN_MIL = SEC_MIL * 60;
    private static final long HOU_MIL = MIN_MIL * 60;
    private static final long DAY_MIL = HOU_MIL * 24;
    private static final long WEEK_MIL = DAY_MIL * 7;

    public static String dateFormat(Long currentTime) {
        return SimpleDateFormat.getDateInstance().format(currentTime);
    }

    public static SimpleDateFormat getSimpleDataFormat(String type) {
        return new SimpleDateFormat(type, Locale.CHINA);
    }

    public static String getDate(long currentTime) {
        long mul = System.currentTimeMillis() - currentTime;
        if (mul < HOU_MIL) {
            return mul / MIN_MIL + "分钟前";
        } else if (mul < DAY_MIL) {
            return mul / HOU_MIL + "小时前";
        } else if (mul < WEEK_MIL) {
            return mul / DAY_MIL + "天前";
        } else {
            return getSimpleDataFormat("yyyy-MM-dd").format(currentTime);
        }

    }

}
