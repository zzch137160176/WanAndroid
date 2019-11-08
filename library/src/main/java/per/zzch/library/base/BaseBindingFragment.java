package per.zzch.library.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import per.zzch.library.utils.ViewModelClassUtil;
import per.zzch.library.widget.ProgressDialog;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/6
 * @desc :
 */
public abstract class BaseBindingFragment<VM extends ViewModel, B extends ViewDataBinding> extends Fragment {

    private int mLayout;

    protected Context mContext;

    /**
     * 等待框
     */
    private ProgressDialog mProgressDialog = null;

    protected VM mViewModel;

    protected B mBinding;

    /**
     * 初始化界面和控件
     */
    protected abstract void initEventAndData();

    /**
     * 执行操作
     */
    public abstract void onActivityCreate();

    public BaseBindingFragment(int layout) {
        this.mLayout = layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,mLayout,container,true);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initEventAndData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityCreate();
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
