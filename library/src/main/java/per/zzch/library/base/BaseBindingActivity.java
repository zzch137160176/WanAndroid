package per.zzch.library.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import per.zzch.library.utils.AppManager;
import per.zzch.library.utils.ViewModelClassUtil;
import per.zzch.library.widget.ProgressDialog;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/4
 * @desc :
 */
public abstract class BaseBindingActivity<VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    public Context mContext;

    /**
     * 等待框
     */
    private ProgressDialog mProgressDialog = null;

    /**
     * layout资源id
     */
    private int mLayout;

    /**
     * viewModel
     */
    protected VM mViewModel;

    /**
     * banding
     */
    protected B mBinding;

    /**
     * 构造方法
     * @param layout resourseId
     */
    public BaseBindingActivity(int layout) {
        this.mLayout = layout;
    }

    /**
     * 初始化界面和控件
     */
    protected abstract void initEventAndData();

    /**
     * 在基类执行完onCreate()，并初始化后，执行的后续操作
     * 相当于派生类的onCreate()
     */
    protected abstract void onCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        mBinding = DataBindingUtil.setContentView(this, mLayout);
        mBinding.setLifecycleOwner(this);
        mContext = this;
        AppManager.getInstance().addActivity(this);
        initEventAndData();
        onCreate();
    }

    @Override
    protected void onDestroy() {
        dissmissLoading();
        AppManager.getInstance().finishActivity(this);
        super.onDestroy();
    }

    /**
     * progressDialog的操作：展示、取消
     */
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
        }
        mProgressDialog.show();
    }

    public void dissmissLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 初始化viewModel
     */
    private void initViewModel() {
        Class<VM> clazz = ViewModelClassUtil.getViewModel(this);
        if (clazz != null) {
            this.mViewModel = ViewModelProviders.of(this).get(clazz);
        }
    }
}
