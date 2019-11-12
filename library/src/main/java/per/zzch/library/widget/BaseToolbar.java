package per.zzch.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import per.zzch.library.R;
import per.zzch.library.listener.ClickListener;
import per.zzch.library.utils.ViewUtil;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/11
 * @desc :
 */
public class BaseToolbar extends FrameLayout implements View.OnClickListener {

    private ImageView mStartImg;
    private ImageView mEndImg;
    private TextView mTitleTV;
    private TextView mEndTV;

    private ClickListener mStartClickListener;
    private ClickListener mEndClickListener;

    public BaseToolbar(@NonNull Context context) {
        super(context);
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 导入布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.base_toolbar, this, false);
        addView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.toolbar_height)));

        // 绑定控件
        mStartImg = findViewById(R.id.iv_start_img);
        mEndImg = findViewById(R.id.iv_end_img);
        mTitleTV = findViewById(R.id.tv_title);
        mEndTV = findViewById(R.id.tv_end_text);

        // 获取TypedArray对象
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseToolbar);

        // 设置title相关属性
        String title = ta.getString(R.styleable.BaseToolbar_title_text);
        int titleColor = ta.getColor(R.styleable.BaseToolbar_title_text_color, Color.WHITE);
        mTitleTV.setText(title);
        mTitleTV.setTextColor(titleColor);

        // 设置末尾文本相关属性
        String endTxt = ta.getString(R.styleable.BaseToolbar_title_end_text);
        int endColor = ta.getColor(R.styleable.BaseToolbar_title_end_color, Color.WHITE);
        int endSize = ta.getDimensionPixelSize(R.styleable.BaseToolbar_title_end_size, -1);
        mEndTV.setTextColor(endColor);
        if (endSize != -1) {
            mEndTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, endSize);
        }
        if (!TextUtils.isEmpty(endTxt)) {
            mEndTV.setText(endTxt);
            mEndTV.setVisibility(VISIBLE);
            mEndTV.setOnClickListener(this);
        }

        // 设置头部图标相关属性
        int startImg = ta.getResourceId(R.styleable.BaseToolbar_title_start_img, -1);
        if (startImg != -1) {
            mStartImg.setImageResource(startImg);
        }
        mStartImg.setOnClickListener(this);
        boolean startEnable = ta.getBoolean(R.styleable.BaseToolbar_title_start_enable, true);
        if (!startEnable) {
            mStartImg.setVisibility(GONE);
        }

        // 设置末尾图标相关属性
        int endImg = ta.getResourceId(R.styleable.BaseToolbar_title_end_img, -1);
        if (endImg != -1) {
            mEndImg.setImageResource(endImg);
            mEndImg.setVisibility(VISIBLE);
            mEndImg.setOnClickListener(this);
        }

        // 设置背景颜色
        int titleBgColor = ta.getResourceId(R.styleable.BaseToolbar_title_bg_color, R.color.colorPrimary);
        setBackgroundResource(titleBgColor);

        // 执行
        ta.recycle();
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setTitle(String title) {
        mTitleTV.setText(title);
    }

    public void setTitle(@StringRes int title) {
        mTitleTV.setText(title);
    }

    public void hideEndImg() {
        mEndImg.setVisibility(GONE);
        mEndImg.setOnClickListener(null);
    }

    public void setTitleBackground(@DrawableRes int resId) {
        setBackgroundResource(resId);
    }

    /**
     * 禁用返回按钮
     */
    public void disableClickBack() {
        mStartImg.setVisibility(GONE);
        mStartImg.setOnClickListener(null);
    }

    public void setStartImg(@DrawableRes int resId) {
        mStartImg.setImageResource(resId);
        mStartImg.setVisibility(VISIBLE);
        mStartImg.setOnClickListener(this);
    }

    public void setEndImg(@DrawableRes int resId) {
        mEndImg.setImageResource(resId);
        mEndImg.setVisibility(VISIBLE);
        mEndImg.setOnClickListener(this);
    }

    public void setEndTxt(String text) {
        mEndImg.setVisibility(GONE);
        mEndTV.setText(text);
        mEndTV.setVisibility(View.VISIBLE);
        mEndTV.setOnClickListener(TextUtils.isEmpty(text) ? null : this);
    }

    public String getEndTxt() {
        return mEndTV.getText().toString();
    }

    public void setStartClickListener(ClickListener startClickListener) {
        mStartClickListener = startClickListener;
    }

    public void setEndClickListener(ClickListener endClickListener) {
        mEndClickListener = endClickListener;
    }

    private void clickTitleStart(View v) {
        // 若有设置监听则执行
        if (mStartClickListener != null) {
            mStartClickListener.onClick(v);
            return;
        }
        // 末设置则默认执行返回
        AppCompatActivity activity = ViewUtil.getAppCompActivity(getContext());
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        // 在lib中不能switch语句来控制id
        int id = v.getId();
        if (id == R.id.iv_start_img) {
            clickTitleStart(v);
        }
        if (id == R.id.iv_end_img || id == R.id.tv_end_text) {
            if (mEndClickListener != null) {
                mEndClickListener.onClick(v);
            }
        }
    }

    /**
     * dataBinding 使用
     */
    public void setTitle_text(String title) {
        mTitleTV.setText(title);
    }
}
