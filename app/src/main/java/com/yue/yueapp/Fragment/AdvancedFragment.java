package com.yue.yueapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yue.yueapp.R;

/**
 * Created by yzz on 2017/8/30.
 * <p>
 * 学习抛物线的进阶。
 * 这是进阶列表
 */

public class AdvancedFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_advanced, container, false);
        return view;
    }
}
