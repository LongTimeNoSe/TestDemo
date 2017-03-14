package com.testdemo.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testdemo.R;

/**
 * Created by XuYanping on 2017/2/20.
 */

public class TopBarView extends RelativeLayout {

    private TextView title;
    private Button leftButton, rightButoon;
    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mTitleParams, mLeftParams, mRightParams;
    //标题属性
    private int titleColor;
    private float titleSize;
    private String titleText;

    //左按钮属性
    private int leftTextColor;
    private Drawable leftTextBackground;
    private String leftText;
    private float leftTextSize;

    //右按钮属性
    private int rightTextColor;
    private Drawable rightTextBackground;
    private String rightText;
    private float rightTextSize;

    //
    private topBarOnClickListener mTopBarOnClickListener;

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable的所有属性的值存储到TypedArray中
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.topBar);

        // 从TypedArray中取出对应的值来为要设置的属性赋值
        titleText = typedArray.getString(R.styleable.topBar_title);
        titleSize = typedArray.getFloat(R.styleable.topBar_titleTextSize, 12);
        titleColor = typedArray.getColor(R.styleable.topBar_titleTextColor, 0);

        leftText = typedArray.getString(R.styleable.topBar_leftText);
        leftTextSize = typedArray.getFloat(R.styleable.topBar_leftTextSize, 10);
        leftTextColor = typedArray.getColor(R.styleable.topBar_leftTextColor, 0);
        leftTextBackground = typedArray.getDrawable(R.styleable.topBar_leftTextBackground);

        rightText = typedArray.getString(R.styleable.topBar_rightText);
        rightTextSize = typedArray.getFloat(R.styleable.topBar_rightTextSize, 10);
        rightTextColor = typedArray.getColor(R.styleable.topBar_rightTextColor, 0);
        rightTextBackground = typedArray.getDrawable(R.styleable.topBar_rightTextBackground);

        // 获取完TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误
        typedArray.recycle();

        leftButton = new Button(context);
        rightButoon = new Button(context);
        title = new TextView(context);

        // 为创建的组件元素赋值,值就来源于我们在引用的xml文件中给对应属性的赋值
        title.setText(titleText);
        title.setTextColor(titleColor);
        title.setTextSize(titleSize);

        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setTextSize(leftTextSize);
        leftButton.setBackground(leftTextBackground);

        rightButoon.setText(rightText);
        rightButoon.setTextColor(rightTextColor);
        rightButoon.setTextSize(rightTextSize);
        rightButoon.setBackground(rightTextBackground);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(title, mTitleParams);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButoon, mRightParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarOnClickListener.onLeftClick();
            }
        });

        rightButoon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarOnClickListener.onRightClick();
            }
        });
    }

    public void setTopBarOnClickListener(topBarOnClickListener topBarOnClickListener) {
        mTopBarOnClickListener = topBarOnClickListener;
    }

    private interface topBarOnClickListener {
        void onLeftClick();

        void onRightClick();
    }
}
