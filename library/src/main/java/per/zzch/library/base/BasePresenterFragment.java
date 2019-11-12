package per.zzch.library.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import per.zzch.library.utils.ToastUtil;
import per.zzch.library.widget.ProgressDialog;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/8
 * @desc   :Fragment 基类。该类中额外加入了Framgent中特殊的生命周期，可作参考。
 */
public abstract class BasePresenterFragment<P extends IPresenter> extends Fragment implements IView {

    // fragment中的context，在onAttach()方法中初始化
    public Context mContext;

    // view中持有的presenter对象
    protected P mPresenter;

    // 等待框
    private ProgressDialog mProgressDialog = null;

    /**
     * 获取ui布局，在onCreateView()中调用
     */
    protected abstract int getLayout();

    /**
     * 初始化presenter，该方法在onActivityCreated()中调用
     */
    protected abstract P createPresenter();

    /**
     * 绑定ui控件，该方法在在onViewCreate()中调用
     */
    protected abstract void onViewCreate(@NonNull View view);

    /**
     * 执行ui操作，该方法在onActivityCreated()中调用
     */
    protected abstract void onCreated(@Nullable Bundle bundle);

    /**
     * Fragment 关联到 Activity 时回调
     * 此时 Activity 已经与 Fragment 关联，通过 Context 向下转型，就可以与 Activity 通信
     * 当然也可以使用 getActivity(),前提是这个 fragment 已经和宿主的 activity 关联，并且没有脱离
     * onAttach 只调用一次。
     */
    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
        super.onAttach(context);
    }

    /**
     * 系统创建 Fragment 的时候回调，介于 onAttach() 和 onCreateView() 之间
     * 一般用于初始化一些数据
     * 值得注意的是，此时 Activity 还在创建中，因此不能在执行一些跟 Activity UI 相关的操作
     * 否则，会出现一些难以预料的问题，比如：NullPointException
     * 如果要对 Activity 上的 UI 进行操作，建议在 onActivityCreated() 中操作
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建 Fragment 需要显示的 View，默认返回 null。
     * 当返回的 View 不为 null 时，View 被销毁时会调用 onDestroyView()
     * 此处应该只进行布局的初始化，而不应该执行耗时操作，如网络请求、数据库读取
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getLayout(), container, false);
    }

    /**
     * 该方法在 onCreateView() 之后会被立即执行
     * 此时可以对 View 对象进行赋值，当然在 onCreateView() 中也可以执行
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 在viewCreated之后，可以通过view.findViewById()绑定控件
        onViewCreate(view);
    }

    /**
     * 当 Activity 执行完 onCreate() 方法后调用
     * 此时可以执行与 Activity 相关的 UI 操作
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        onCreated(savedInstanceState);
    }

    /**
     * 表示销毁 Fragment 相关联的 UI 布局，清除所有跟视图相关的资源。
     * 不一定在 Activity 的 onDestroy() 方法中调用
     * 如：Fragment 与 ViewPager 结合使用时
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 销毁 Fragment 对象的时候调用，一般是调用 Activity 的 onDestroy() 的时候执行
     */
    @Override
    public void onDestroy() {
        dissmissLoading();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    /**
     * 解除 Fragment 与 Activity 的关联
     */
    @Override
    public void onDetach() {
        super.onDetach();
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
