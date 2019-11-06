package per.zzch.library.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import per.zzch.library.listener.ItemClickListener;
import per.zzch.library.listener.ItemLongClickListener;

/**
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/10/8
 * @desc   :
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter<T>.ViewHolder> {

    private List<T> mDataList;

    private ItemClickListener<T> mItemClickListener;
    private ItemLongClickListener<T> mItemLongClickListener;

    public abstract int getLayoutResouse();

    public abstract void bindView(@NonNull ViewHolder holder, int position);

    public void bindRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
    }

    public List<T> getmDataList() {
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ItemClickListener<T> listener) {
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(ItemLongClickListener<T> listener) {
        mItemLongClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResouse(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindView(holder, position);
        holder.itemView.setOnClickListener(v -> {
            if (mItemClickListener != null) {
                mItemClickListener.itemClickDoing(mDataList.get(position), position);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (mItemLongClickListener != null) {
                mItemLongClickListener.itemLongClickDoing(mDataList.get(position), position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
