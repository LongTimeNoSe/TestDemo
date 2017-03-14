package com.testdemo.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;

/**
 * Created by XuYanping on 2017/2/8.
 */

public class DefaultItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnItemTouchCallbackListener mOnItemTouchCallbackListener;

    /**
     * 是否可以拖拽
     */
    private boolean isCanDrag = true;
    /**
     * 是否可以被滑动
     */
    private boolean isCanSwipe = true;

    public DefaultItemTouchHelperCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        mOnItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    /**
     * 设置Item操作的回调，去更新UI和数据源
     *
     * @param onItemTouchCallbackListener
     */
    public void setOnItemTouchCallbackListener(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.mOnItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    /**
     * 设置是否可以拖拽
     *
     * @param canDrag true 是 ，false 否
     */
    public void setCanDrag(boolean canDrag) {
        isCanDrag = canDrag;
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setCanSwipe(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    /**
     * 当Item被长按的时候是否可以被拖拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    /**
     * Item是否可以被滑动(H：左右滑动，V：上下滑动)
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            // flag如果值是0，相当于这个功能被关闭
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            return makeMovementFlags(dragFlag, swipeFlag);
        } else if (layoutManager instanceof LinearLayoutManager) {

            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();
            int dragFlag = 0;
            int swipeFlag = 0;
            if (orientation == LinearLayout.HORIZONTAL) { //如果是横向布局
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

            } else if (orientation == LinearLayout.VERTICAL) { //如果是竖向布局
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }

        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (mOnItemTouchCallbackListener != null) {
            return mOnItemTouchCallbackListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mOnItemTouchCallbackListener != null) {
            mOnItemTouchCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }


    public interface OnItemTouchCallbackListener {

        /**
         * 当某个item被滑动删除的时候回调
         *
         * @param adapterPosition 当前item的position
         */
        void onSwiped(int adapterPosition);

        /**
         * 当两个item位置互换的时候回调
         *
         * @param srcPosition    当前拖拽的item的position
         * @param targetPosition 目的地的item的position
         */
        boolean onMove(int srcPosition, int targetPosition);
    }
}
