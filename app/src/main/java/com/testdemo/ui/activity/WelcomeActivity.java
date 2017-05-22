package com.testdemo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_welcome)
    ImageView mIvWelcome;
    @BindView(R.id.second)
    TextView mSecond;
    @BindView(R.id.tv_third)
    TextView mTvThird;
    @BindView(R.id.tv_first)
    TextView mTvFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        startAnimator();

    }

    private void startAnimator() {

        Animator anim = AnimatorInflater.loadAnimator(WelcomeActivity.this, R.animator.welcome_animator);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mTvFirst.setVisibility(View.VISIBLE);
                startAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setTarget(mIvWelcome);
        anim.start();

    }

    private void startAnimation() {

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        final Animation anim_1 = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        final Animation anim_2 = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        anim_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                PromptUtil.toastShowShort(WelcomeActivity.this, "开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                PromptUtil.toastShowShort(WelcomeActivity.this, "结束");

                anim_2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                                startActivity(new Intent(WelcomeActivity.this, RecyclerViewTwoActivity.class));
                                WelcomeActivity.this.finish();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mTvThird.setVisibility(View.VISIBLE);
                        mTvThird.setAnimation(anim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mSecond.setVisibility(View.VISIBLE);
                mSecond.setAnimation(anim_2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//                PromptUtil.toastShowShort(WelcomeActivity.this, "重复");
            }
        });
        mTvFirst.setAnimation(anim_1);

    }
}
