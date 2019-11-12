package per.zzch.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/26
 * @desc   :
 */
public class ViewDelegate {

    private Context mContext;

    private View mView;

    public ViewDelegate(View view, Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mView = view;
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NullEditText);
        typedArray.getBoolean(R.styleable.NullEditText_show_clean_btn, false);
        typedArray.recycle();
    }

}
