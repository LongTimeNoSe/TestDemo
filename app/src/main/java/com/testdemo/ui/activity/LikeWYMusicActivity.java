package com.testdemo.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.testdemo.R;
import com.testdemo.adapter.LikeWYMusicAdapter;
import com.testdemo.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class LikeWYMusicActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.header_image)
    ImageView mHeaderImage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    List<String> mList;

    LikeWYMusicAdapter mAdapter;
    // 在多大范围内变色
    private int slidingDistance;
    // 这个是高斯图背景的高度
    private int imageBgHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_wymusic);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initView();
        initSlideShapeTheme();

    }

    private void initView() {

        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        mList = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            mList.add("This is" + i + "item");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new LikeWYMusicAdapter(LikeWYMusicActivity.this, mList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * 初始化滑动渐变
     */
    private void initSlideShapeTheme() {
        setImgHeaderBg();

        // toolbar的高度
        int toolbarHeight = mToolbar.getLayoutParams().height;
        // toolbar+状态栏的高度
        final int headerBgHeight = toolbarHeight + StatusBarUtil.getStatusBarHeight(this);

        // 使背景图向上移动到图片的最底端，保留toolbar+状态栏的高度
        mHeaderImage.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams params = mHeaderImage.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) mHeaderImage.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
        mHeaderImage.setImageAlpha(0);

        // 为头部是View的界面设置状态栏透明
        StatusBarUtil.setTranslucentForImageView(this, 0, mToolbar);

        ViewGroup.LayoutParams imgItemBgparams = mHeaderImage.getLayoutParams();
        // 获得高斯图背景的高度
        imageBgHeight = imgItemBgparams.height;

        // 监听改变透明度
        initScrollViewListener();
    }

    /**
     * 加载titlebar背景,加载后将背景设为透明
     */
    private void setImgHeaderBg() {
        Glide.with(this).load("")
//                .placeholder(R.drawable.stackblur_default)
                .error(R.drawable.backgroung).bitmapTransform(new BlurTransformation(this, 14, 3))// 设置高斯模糊
                .listener(new RequestListener<String, GlideDrawable>() {//监听加载状态
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mToolbar.setBackgroundColor(Color.TRANSPARENT);
                        mHeaderImage.setImageAlpha(0);
                        mHeaderImage.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(mHeaderImage);
    }


    private void initScrollViewListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollChangeHeader(dx);
            }
        });

        int titleBarAndStatusHeight = (int) (getResources().getDimension(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(this));
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (getResources().getDimension(R.dimen.nav_bar_height_more));
    }

    /**
     * 根据页面滑动距离改变Header透明度方法
     */
    private void scrollChangeHeader(int scrolledY) {

//        DebugUtil.error("---scrolledY:  " + scrolledY);
//        DebugUtil.error("-----slidingDistance: " + slidingDistance);

        if (scrolledY < 0) {
            scrolledY = 0;
        }
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);
        Drawable drawable = mHeaderImage.getDrawable();
//        DebugUtil.error("----alpha:  " + alpha);

        if (drawable != null) {
            if (scrolledY <= slidingDistance) {
                // title部分的渐变
                drawable.mutate().setAlpha((int) (alpha * 255));
                mHeaderImage.setImageDrawable(drawable);
            } else {
                drawable.mutate().setAlpha(255);
                mHeaderImage.setImageDrawable(drawable);
            }
        }
    }

}
