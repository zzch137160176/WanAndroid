package per.zzch.library.base;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/8
 * @desc   :presenter 基类,持有view对象
 */
public class BasePresenter<V extends IView> implements IPresenter {

    protected V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
