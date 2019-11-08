package per.zzch.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/9/27
 * @desc :
 */
public class ProgressDialog extends Dialog {

    public ProgressDialog(Context context) {
        super(context);
        setContentView(R.layout.layout_progress);
        setCancelable(false);
    }

    public void setMessage(String msg) {
        TextView msgTv = findViewById(R.id.dialog_message);
        msgTv.setText(msg);
    }

    public void changeCancelable(Boolean cancelable) {
        setCancelable(cancelable);
    }
}
