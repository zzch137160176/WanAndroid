package per.zzch.library.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import per.zzch.library.R;
import per.zzch.library.adapter.StringRecyclerAdapter;
import per.zzch.library.listener.ItemClickListener;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date :2019/11/8
 * @desc :
 */
public class DefaultPopup {

    private PopupWindow mPopup;

    private StringRecyclerAdapter mAdapter;

    public DefaultPopup(Context context, int width, int height, ItemClickListener<String> listener) {
        mAdapter = new StringRecyclerAdapter();
        mAdapter.setOnItemClickListener(listener);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.popup_recycler_string, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);
        mPopup = new PopupWindow(recyclerView, width, height, true);
        mPopup.setTouchable(true);
    }

    public void show(ArrayList<String> list, View view) {
        mAdapter.setDataList(list);
        mPopup.showAsDropDown(view);
    }

    public void dismiss() {
        mPopup.dismiss();
    }
}
