package per.zzch.library.base;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/8
 * @desc   :view 接口,定义一些通用界面控制的方法
 */
public interface IView {

    void showSuccess(String msg);

    void showFailed(String msg);

    /**
     * 显示等待框
     */
    void showLoading();

    /**
     * 隐藏等待框
     */
    void dissmissLoading();

}
