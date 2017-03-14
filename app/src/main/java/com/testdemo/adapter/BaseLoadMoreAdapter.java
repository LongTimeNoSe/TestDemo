package com.testdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testdemo.R;

import java.util.List;

/**
 * Created by XuYanping on 2017/2/8.
 */

public abstract class BaseLoadMoreAdapter<T> extends RecyclerView.Adapter {

    private Context mContext;
    private List<T> mDatas;
    private int layoutId;

    private boolean isLoading = false;
    private static final int TYPE_ITEM = 101;
    private static final int TYPE_PROGRESS = 102;

    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;
    private onLoadMoreListener mOnLoadMoreListener;

    public BaseLoadMoreAdapter(Context context, RecyclerView recyclerView, List<T> datas, int layoutId) {
        mDatas = datas;
        mContext = context;
        this.layoutId = layoutId;
        init(recyclerView);
    }

    public abstract void convert(Context mContext, RecyclerView.ViewHolder holder, T t);

    private void init(RecyclerView view) {

        view.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && dy > 0 && lastVisibleItemPosition >= totalItemCount - 1) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void updateData(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
            BaseViewHolder baseViewHolder = new BaseViewHolder(itemView);
            return baseViewHolder;
        } else {
            View progressView = LayoutInflater.from(mContext).inflate(R.layout.progress_item, parent, false);
            ProgressViewHolder progressViewHolder = new ProgressViewHolder(progressView);
            return progressViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BaseViewHolder) {
            convert(mContext, holder, mDatas.get(position));
            ((BaseViewHolder) holder).mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
            ((BaseViewHolder) holder).mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public interface onLoadMoreListener {
        void onLoadMore();
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnLoadMoreListener(onLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }
}
