package com.testdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recyclerviewlibrary.SimpleRecyclerViewAdapter;
import com.recyclerviewlibrary.SimpleRecyclerViewViewHolder;
import com.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XuYanping on 2017/4/6.
 */

public class RecyclerViewTwoActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<String> mList = new ArrayList<String>();
    MyAdapter mAdapter = new MyAdapter();
    SimpleItemTouchHelperCallback mCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        addData();
        mRecyclerView.setAdapter(new SimpleRecyclerViewAdapter(RecyclerViewTwoActivity.this, mList, R.layout.adapter_recycleview_item) {
            @Override
            public void convert(SimpleRecyclerViewViewHolder holder, Object o) {
                CardView cardView = holder.getView(R.id.cardView);
                int screenWidthTwo = getResources().getDisplayMetrics().widthPixels;
                cardView.setLayoutParams(new LinearLayout.LayoutParams(screenWidthTwo, LinearLayout.LayoutParams.WRAP_CONTENT));
                holder.setText(R.id.tv, o + "---");
            }
        });
//        mCallback = new SimpleItemTouchHelperCallback(mAdapter);
//        ItemTouchHelper helper = new ItemTouchHelper(mCallback);
//        helper.attachToRecyclerView(mRecyclerView);
    }

    private void addData() {
        for (int i = 0; i < 6; i++) {
            mList.add("item" + i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MyItemTouchHelper {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycleview_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder) {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.BindItem(mList.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public void onItemDissmiss(int position) {
            mList.remove(position);
            notifyItemRemoved(position);
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv)
            TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void BindItem(String strs) {
                mTv.setText(strs);
            }
        }
    }

    public interface MyItemTouchHelper {

        //数据交换
//    void onItemMove(int fromPosition, int toPosition);

        //数据删除
        void onItemDissmiss(int position);
    }

    class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

        private MyItemTouchHelper mHelper;

        public SimpleItemTouchHelperCallback(MyItemTouchHelper helper) {
            mHelper = helper;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.LEFT;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        mHelper.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//        return true;
//    }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mHelper.onItemDissmiss(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }
    }
}
