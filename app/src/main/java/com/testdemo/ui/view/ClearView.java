package com.testdemo.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.testdemo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XuYanping on 2017/2/20.
 */

public class ClearView extends View {
    Paint p = new Paint();// 画笔
    RectF oval;// 控制扇形大小的矩形
    private int state;// 0 反转，1，正转
    int startAngle = -90;// 起始角度
    int sweepAngle = 360;// 经过角度
    private int[] backSpeed = new int[]{-6, -6, -10, -10, -10, -12};
    private int backIndex;
    private int[] forSpeed = new int[]{12, 12, 12, 12, 10, 10, 10, 8, 8, 8, 6};
    private int forIndex;
    static final int MOVE_FORWARD = 1;// /正转
    static final int MOVE_BACKWARD = 0;// 反转
    private boolean isRunning;// 禁止重复运行timer的状态控制变量
    private int circleColor;
    /**
     * 可选，内部接口对象
     */
    private NumberListener listener;

    public ClearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        p.setAntiAlias(true);
        // 从外部资源中获取颜色值
        if (isInEditMode()) {
            return;
        }
        setViewColor(getResources().getColor(R.color.clean_circle));
        p.setColor(circleColor);
        //	setAngle(360);

    }

    // 设置圆环颜色
    public void setViewColor(int circleColor) {
        this.circleColor = circleColor;
    }

    // 重写的测量方法，将布局中控件的宽高转化为扇形外面矩形的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        oval = new RectF(0, 0, width, height);
        setMeasuredDimension(width, height);
    }

    // 不带动画的设置角度方法
    private void setAngle(int i) {
        // TODO Auto-generated method stub
        sweepAngle = 360;
        postInvalidate();
        isRunning = false;
    }

    // 带动画的设置角度方法
    public void setAngleWithAnim(final int angle) {
        // 加入控制，当动画正在进行时，界面上的点击将不会引起重复运行动画
        if (isRunning) {
            return;
        }
        isRunning = true;
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                switch (state) {
                    case MOVE_BACKWARD:
                        // 速度控制
                        sweepAngle += backSpeed[backIndex++];
                        backIndex = backIndex == backSpeed.length ? backSpeed.length - 1
                                : backIndex;

                        if (sweepAngle <= 0) {
                            state = MOVE_FORWARD;
                            sweepAngle = 0;
                        }
                        break;
                    case MOVE_FORWARD:
                        // 速度控制
                        sweepAngle += forSpeed[forIndex++];
                        forIndex = forIndex == forSpeed.length ? forSpeed.length - 1
                                : forIndex;
                        if (sweepAngle >= angle) {
                            sweepAngle = angle;
                            timer.cancel();
                            isRunning = false;
                            state = 0;
                        }
                        break;
                }
                postInvalidate();
                /**
                 * 可选--用接口监听当前的角度
                 * */
                if (listener != null) {
                    listener.setNumber(sweepAngle);
                }


            }
        };
        timer.schedule(task, 24, 24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制扇形
        // 矩形，开始角度，经过角度，使用中心，画笔
        canvas.drawArc(oval, startAngle, sweepAngle, true, p);
    }

    /***
     * 可选---接口对象的getter/setter
     */
    public NumberListener getListener() {
        return listener;
    }

    public void setListener(NumberListener listener) {
        this.listener = listener;
    }

    /***
     * 可选---制作接口监听当前的角度，用来把主界面的数字UI同步变化
     */

    public interface NumberListener {
        void setNumber(int i);
    }
}
