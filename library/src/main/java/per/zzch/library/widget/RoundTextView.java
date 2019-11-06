package per.zzch.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/10/24
 * @desc   :
 */
public class RoundTextView extends AppCompatTextView {

    public RoundTextView(Context context) {
        super(context);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setBackground(getResources().getDrawable(R.drawable.tv_bg));
    }

    /**
     * 回调该方法来测量并设置view的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置宽高
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 绘制view
     * Canvas 对象，相当于画布
     * 在其它地方使用Canvas对象时，可以通过 new Canvas(bitmap); 来实现
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 通过判断测量的模式，给出不同的测量值
     */
    private int measureHeight(int measureSpec) {
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

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
