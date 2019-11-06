package per.zzch.library.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/27
 * @desc   :Activity管理类
 * <p>
 * 包含一个activity栈，存储activity并对其中activity进管理
 */
public class AppManager {

    private Stack<Activity> activityStack;

    private AppManager() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
    }

    public static AppManager getInstance() {
        return AppHolder.instance;
    }

    public void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 结束当前（栈顶）activity
     */
    public void finishActivity() {
        if (activityStack.size() == 0) {
            return;
        }
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束全部activity
     */
    public void finishAll() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    private static class AppHolder {
        private static final AppManager instance = new AppManager();
    }
}
