package per.zzch.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/24
 * @desc   :
 */
public class InputEditText extends AppCompatEditText {

    private Drawable mCleanBtn;

    private boolean isShowCleanBtn;

    public InputEditText(Context context) {
        super(context);
        init();
    }

    public InputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        // 设置背景
        setBackground(getResources().getDrawable(R.drawable.edit_bg));

//        setTextCursorDrawable(getResources().getDrawable(R.drawable.black_cursor));

        // 添加清空按钮
        Drawable[] compoundDrawables = this.getCompoundDrawables();
        if (mCleanBtn == null) {
            mCleanBtn = getResources().getDrawable(R.drawable.ic_clean_input);
            // 重新设置四个方位的素材，顺序为左，上，右，下
            setCompoundDrawablesWithIntrinsicBounds(
                    compoundDrawables[0],compoundDrawables[1],mCleanBtn,compoundDrawables[2]);
        }
    }

    public void setShowCleanBtn(boolean flag) {
        isShowCleanBtn = flag;
        if (isShowCleanBtn) {
            setCompoundDrawables(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], null,
                    getCompoundDrawables()[3]);
        } else {
            setCompoundDrawables(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], mCleanBtn,
                    getCompoundDrawables()[3]);
        }
    }

    /**
     * 回调该方法来测量并设置view的大小
     *
     * @param widthMeasureSpec  view的宽，在wrap_content时需要计算
     * @param heightMeasureSpec view的高，在wrap_content时需要计算
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        // 设置宽高
//        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    /**
     * 绘制view
     *
     * @param canvas Canvas 对象，相当于画布
     *               在其它地方使用Canvas对象时，可以通过 new Canvas(bitmap); 来实现
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 通过判断测量的模式，给出不同的测量值
     *
     * @param measureSpec 需要计算的量
     * @return 计算后得出的结果
     */
    private int measure(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {  // 精确值模式，layout_width为固定值或match_parent时
            result = specSize;
        } else {    // 非精确模式时
            result = 200;   // 给定一个默认值
            if (specMode == MeasureSpec.AT_MOST) {  // 最大值模式，layout_width为wrap_content时
                result = Math.min(result, specSize);    // 当为wrap_content时，需要进行最后的比较，取最小值
            }
        }
        return result;
    }

}
