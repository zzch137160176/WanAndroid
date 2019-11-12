package per.zzch.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import per.zzch.library.R;


/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/10/24
 * @desc :
 */
public class RoundTextView extends AppCompatTextView {

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);

        int color = ta.getColor(R.styleable.RoundTextView_text_bg_color, Color.rgb(1, 142, 216));
        int colorPressed = ta.getColor(R.styleable.RoundTextView_text_pressed_bg_color, Color.rgb(1, 142, 216));
        float radius = ta.getDimension(R.styleable.RoundTextView_text_radius, 5);

        //外部矩形弧度
        float[] outerRadian = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        //内部矩形与外部矩形的距离
//        RectF insetDistance = new RectF(50, 50, 50, 50);
        //内部矩形弧度
//        float[] insideRadian = new float[]{20, 20, 20, 20, 20, 20, 20, 20};
        //如果insetDistance与insideRadian设为null亦可
        RoundRectShape roundRectShape = new RoundRectShape(outerRadian, null, null);
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        //指定填充颜色
        drawable.getPaint().setColor(color);
        //指定填充模式
        drawable.getPaint().setStyle(Paint.Style.FILL);

        setBackground(drawable);

        ta.recycle();

        setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //更改为按下时的背景图片
                v.setBackgroundColor(colorPressed);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                //改为抬起时的图片
                v.setBackgroundColor(color);
                // onTouch优先级高于onTouchEvent和onClick，在返回true，消耗该事件的情况下，
                // 要调用perFormClick()来让后续是事件执行
                v.performClick();
            }
            return true;
        });

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
