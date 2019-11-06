package per.zzch.library.base;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/24
 * @desc   :
 */
public class BaseModel<T> implements IModel{

    protected T mListener;

    public BaseModel(T listener) {
        mListener = listener;
    }

}
