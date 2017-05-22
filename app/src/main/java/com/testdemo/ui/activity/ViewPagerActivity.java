package com.testdemo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.testdemo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner mBanner;

    List<String> imageList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        initBanner();

    }

    private void initBanner() {

        imageList.add("https://pic4.zhimg.com/v2-1768fa9c5c0b966a90f6f762da73d9cb.jpg");
        imageList.add("https://pic1.zhimg.com/v2-ff5063b5e151cfb654f2bee144e8f2ac.jpg");
        imageList.add("https://pic2.zhimg.com/v2-ed27b07786396de3df5d6c3cf6c75d05.jpg");
        imageList.add("https://pic2.zhimg.com/v2-9c100252abe543914ba52a81cc2d9791.jpg");
        imageList.add("https://pic1.zhimg.com/v2-ffedd43aaa253d845b19846828927ec8.jpg");

        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOut);
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImageLoader(new GlideImageLoder());
        mBanner.setImages(imageList);
        mBanner.start();
    }


    class GlideImageLoder extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(context).load(path).into(imageView);
        }
    }
}
