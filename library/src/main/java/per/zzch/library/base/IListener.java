package per.zzch.library.base;

import per.zzch.library.retrofit.Result;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/24
 * @desc   :
 */
public interface IListener<T> {

    /**
     * 登录成功监听
     */
    void onSuccess(Result<T> result);

    /**
     * 登录失败监听
     */
    void onFaild(String msg);

}
