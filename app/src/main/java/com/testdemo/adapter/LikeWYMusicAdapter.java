package com.testdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XuYanping on 2017/3/16.
 */

public class LikeWYMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    List<String> mList;

    public LikeWYMusicAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycleview_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.bindItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.cardView)
        CardView mCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCardView.setLayoutParams(new LinearLayout.LayoutParams(mContext.getResources().getDisplayMetrics().widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        public void bindItem(String str) {
            mTv.setText(str);
        }
    }
}
