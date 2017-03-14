package com.testdemo.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.testdemo.R;
import com.testdemo.adapter.BaseAdapter;
import com.testdemo.adapter.BaseLoadMoreAdapter;
import com.testdemo.adapter.BaseViewHolder;
import com.testdemo.utils.DefaultItemTouchHelperCallback;
import com.testdemo.utils.PromptUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<String> mData = new ArrayList<>();
    private DragSwipeAdapter mAdapter;
    private MyAdapter myAdapter;

    private int page = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    final List<String> data = new ArrayList<String>();
                    for (int i = 0; i < 10; i++) {
                        data.add("refresh--" + new Random().nextInt(10));
                    }
                    myAdapter.updateData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                case 2:
                    page++;
                    final List<String> dataTwo = new ArrayList<String>();
                    for (int i = 0; i < 6; i++) {
                        dataTwo.add("page-" + page + "-" + "item " + i);
                    }
                    myAdapter.addAll(dataTwo);
                    myAdapter.setLoading(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //只有左右滑动删除，上下拖拽功能布局
//        setContentView(R.layout.activity_recycler_view);
        //只有下拉刷新、上拉加载布局
        setContentView(R.layout.layout_pullrefresh);
        ButterKnife.bind(this);
        init(2);
        addListener(2);
    }

    /**
     * @param type 1:布局一加载Adapter；2：布局二加载Adapter
     */
    private void init(int type) {
        for (int i = 0; i < 20; i++) {
            mData.add("item" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        if (type == 1) {
            mAdapter = new DragSwipeAdapter(this, mData, R.layout.item);
            mRecyclerView.setAdapter(mAdapter);

        }
        if (type == 2) {
            myAdapter = new MyAdapter(this, mRecyclerView, mData, R.layout.item);
            mRecyclerView.setAdapter(myAdapter);
        }
        setItemTouchHelper();
        PromptUtil.snackbarShow(getWindow().getDecorView(), "支持长按拖拽、左右滑动删除的哦(⊙o⊙)哦");
    }

    /**
     * 左右滑动删除，上下拖拽
     *
     */
    private void setItemTouchHelper() {
        DefaultItemTouchHelperCallback mCallback = new DefaultItemTouchHelperCallback(new DefaultItemTouchHelperCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                if (mData != null) {
                    mData.remove(adapterPosition);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                if (mData != null) {
                    // 更换数据源中的数据Item的位置
                    Collections.swap(mData, srcPosition, targetPosition);
                    // 更新UI中的Item的位置，主要是给用户看到交互效果
                    myAdapter.notifyItemMoved(srcPosition, targetPosition);
                    return true;
                }
                return false;
            }
        });

        mCallback.setCanDrag(true);
        mCallback.setCanSwipe(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * @param type 1:布局一监听；2：布局二监听
     */
    private void addListener(int type) {

        if (type == 1) {
            mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });

            mAdapter.setonLongItemClickListener(new BaseAdapter.onLongItemClickListener() {
                @Override
                public void onLongItemClick(View view, int postion) {

                }
            });
        }
        if (type == 2) {
            mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshData();
                }
            });
            myAdapter.setOnLoadMoreListener(new BaseLoadMoreAdapter.onLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    loadMore();
                }
            });
            myAdapter.setOnItemClickListener(new BaseLoadMoreAdapter.onItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
            myAdapter.setOnItemLongClickListener(new BaseLoadMoreAdapter.onItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }
    }

    private void refreshData() {
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessageDelayed(message, 2000);
    }

    private void loadMore() {
        Message message = new Message();
        message.what = 2;
        mHandler.sendMessageDelayed(message, 2000);
    }

    /**
     * 左右滑动删除，上下拖拽移位Adapter
     */
    class DragSwipeAdapter extends BaseAdapter<String> {
        public DragSwipeAdapter(Context context, List<String> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        protected void convert(Context mContext, BaseViewHolder holder, String s) {
            holder.setText(R.id.tv, s);
        }
    }

    /**
     * 下拉刷新、上拉加载Adapter
     */
    class MyAdapter extends BaseLoadMoreAdapter<String> {
        public MyAdapter(Context context, RecyclerView recyclerView, List<String> datas, int layoutId) {
            super(context, recyclerView, datas, layoutId);
        }

        @Override
        public void convert(Context mContext, RecyclerView.ViewHolder holder, String s) {
            if (holder instanceof BaseViewHolder) {
                ((BaseViewHolder) holder).setText(R.id.tv, s);
            }
        }
    }
}
