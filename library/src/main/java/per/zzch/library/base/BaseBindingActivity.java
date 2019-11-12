package per.zzch.library.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import per.zzch.library.utils.ViewModelClassUtil;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/4
 * @desc :
 */
public abstract class BaseBindingActivity<VM extends ViewModel, B extends ViewDataBinding> extends BaseActivity {

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
     *
     * @param layout resourseId
     */
    public BaseBindingActivity(int layout) {
        super(layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        mBinding = DataBindingUtil.setContentView(this, mLayout);
        mBinding.setLifecycleOwner(this);
        initEventAndData();
        onCreate();
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
