package per.zzch.library.base;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/8
 * @desc   :Presenter 接口,定义绑定view和view解绑的方法
 */
public interface IPresenter {

    /**
     * 让 presenter 中持有view的对象,方便通知view更新界面
     * @param view
     */
//    void attachView(V view);

    /**
     * 解绑,释放资源
     */
    void detachView();
}
