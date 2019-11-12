package per.zzch.library.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import per.zzch.library.utils.ToastUtil;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/9/27
 * @desc :Activity基类
 * <p>
 * 在基类中做了一系列的初始化处理：
 * 1、获取activity实例，避免占用this；
 * 2、将新建的activity加入管理栈，方便管理；
 * 3、重写onDestroy方法，回收资源；
 * 4、初始化等待框及其相关方法。
 * <p>
 * 基类包括一个抽象方法，该抽象方法在onCreate中自动调用，派生类只需要重写该方法即可
 */
public abstract class BasePresenterActivity<P extends IPresenter> extends BaseActivity implements IView {

    /**
     * 持有 presenter对象,方便向presenter请求数据
     */
    protected P mPresenter;

    /**
     * 创建Presenter
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    public BasePresenterActivity(int layout) {
        super(layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 先创建presenter,再调用父类的onCreate()
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        initEventAndData();
        onCreate();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    /**
     * 显示成功消息
     */
    @Override
    public void showSuccess(String msg) {
        ToastUtil.show(msg);
    }

    /**
     * 显示失败消息
     */
    @Override
    public void showFailed(String msg) {
        ToastUtil.show(msg);
    }

}
