package com.testdemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.testdemo.R;
import com.testdemo.ui.activity.LikeWYMusicActivity;
import com.testdemo.ui.activity.ToolBarActivity;
import com.testdemo.utils.PromptUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by XuYanping on 2017/3/10.
 */

public class ShowFragment extends Fragment {

    String type;
    @BindView(R.id.show)
    TextView mShow;
    @BindView(R.id.rl_layout)
    CoordinatorLayout mRlLayout;
    @BindView(R.id.bt_floating_action)
    FloatingActionButton mBtFloatingAction;

    boolean isFirstClick = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments().getString("type") == null) {
            type = "default";
        } else {
            type = getArguments().getString("type");
        }
        View view = inflater.inflate(R.layout.fragment_show, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {


        switch (type) {
            case "first":
                mShow.setText("仿网易云音乐歌单详情");
                mShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(getActivity(), LikeWYMusicActivity.class));
                        startActivity(new Intent(getActivity(), ToolBarActivity.class));
                    }
                });

                break;
            case "second":
                mShow.setText(type);
                break;
            case "third":
                mShow.setText(type);
                break;
            default:
                mShow.setText(type);
                break;
        }

    }

    @OnClick(R.id.bt_floating_action)
    public void onClick() {

        if (isFirstClick) {
            PromptUtil.snackbarShow(mRlLayout, "Click");
            mBtFloatingAction.setImageResource(R.drawable.map);
            isFirstClick = false;
        } else {
            PromptUtil.snackbarShow(mRlLayout, "NextClick");
            mBtFloatingAction.setImageResource(R.mipmap.ic_launcher);
            isFirstClick = true;
        }
    }
}
