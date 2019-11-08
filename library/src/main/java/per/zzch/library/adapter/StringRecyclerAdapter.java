package per.zzch.library.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import per.zzch.library.R;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/8
 * @desc :
 */
public class StringRecyclerAdapter extends BaseRecyclerAdapterJ<String> {
    @Override
    public int getLayoutResouse() {
        return R.layout.item_recycler_string;
    }

    @Override
    public void bindView(@NonNull ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.tv_content);
        textView.setText(getDataList().get(position));
    }
}
