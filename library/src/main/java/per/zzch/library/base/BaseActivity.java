package per.zzch.library.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import per.zzch.library.utils.AppManager;
import per.zzch.library.utils.ToastUtil;
import per.zzch.library.widget.ProgressDialog;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/9/27
 * @desc   :Activity基类
 * <p>
 * 在基类中做了一系列的初始化处理：
 * 1、获取activity实例，避免占用this；
 * 2、将新建的activity加入管理栈，方便管理；
 * 3、重写onDestroy方法，回收资源；
 * 4、初始化等待框及其相关方法。
 * <p>
 * 基类包括一个抽象方法，该抽象方法在onCreate中自动调用，派生类只需要重写该方法即可
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    public Context mContext;

    // 持有 presenter对象,方便向presenter请求数据
    protected P mPresenter;

    // 等待框
    private ProgressDialog mProgressDialog = null;

    /**
     * 获取界面ID
     * @return layoutId
     */
    protected abstract int getLayout();

    /**
     * 初始化界面和控件
     */
    protected abstract void initEventAndData();

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    /**
     * 在基类执行完onCreate()，并初始化后，执行的后续操作
     * 相当于派生类的onCreate()
     */
    protected abstract void onCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 先创建presenter,再调用父类的onCreate()
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getInstance().addActivity(this);
        setContentView(getLayout());
        initEventAndData();
        onCreate();
    }

    @Override
    protected void onDestroy() {
        dissmissLoading();
        AppManager.getInstance().finishActivity(this);
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

    /**
     * progressDialog的操作：展示、取消
     */
    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
        }
        mProgressDialog.show();
    }

    @Override
    public void dissmissLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
