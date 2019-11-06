package per.zzch.library.utils;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/4
 * @desc :
 */
public class ViewModelClassUtil {

    public static <T> Class<T> getViewModel(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass);
        if (tClass == null || AndroidViewModel.class.equals(tClass)) {
            return null;
        }
        return tClass;
    }

    private static <T> Class<T> getGenericClass(Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type == null) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            return null;
        }
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        for (Type t :
                types) {
            Class<T> tClass = (Class<T>) t;
            if (ViewModel.class.isAssignableFrom(tClass)) {
                return tClass;
            }
        }
        return null;
    }
}
