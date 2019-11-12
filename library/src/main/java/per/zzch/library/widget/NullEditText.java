package per.zzch.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/10/24
 * @desc :输入框为一根简单的线，右端有清空按钮
 */
public class NullEditText extends AppCompatEditText {

    public Drawable mCleanBtn;

    private boolean isShowClean;

    public NullEditText(Context context) {
        this(context, null);
    }

    public NullEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public NullEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        Drawable[] drawables = this.getCompoundDrawablesRelative();
        // 添加清空按钮
        if (mCleanBtn == null) {
            mCleanBtn = getResources().getDrawable(R.drawable.ic_clean_input, null);
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(
                drawables[0], drawables[1],
                mCleanBtn, drawables[3]);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NullEditText);

        boolean flag = ta.getBoolean(R.styleable.NullEditText_show_clean_btn, false);
        setShowClean(flag);

        setBackgroundDrawable(null);

        ta.recycle();

    }

    public void setShowClean(boolean flag) {
        isShowClean = flag;
        Drawable[] drawables = this.getCompoundDrawablesRelative();
        if (isShowClean) {
            setCompoundDrawablesRelative(drawables[0], drawables[1], mCleanBtn, drawables[3]);
        } else {
            // 重新设置四个方位的素材，顺序为左，上，右，下
            setCompoundDrawablesRelative(drawables[0], drawables[1], null, drawables[3]);
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        // 精确值模式，layout_width为固定值或match_parent时
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
            // 非精确模式时
        } else {
            // 给定一个默认值
            result = 200;
            // 最大值模式，layout_width为wrap_content时
            if (specMode == MeasureSpec.AT_MOST) {
                // 当为wrap_content时，需要进行最后的比较，取最小值
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
