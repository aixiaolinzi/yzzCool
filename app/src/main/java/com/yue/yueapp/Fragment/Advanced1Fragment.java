package com.yue.yueapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yue.yueapp.R;
import com.yue.yueapp.utils.Logger;

import java.util.ArrayList;

/**
 * Created by yzz on 2017/8/30.
 * <p>
 * 学习抛物线的进阶。
 * 这是进阶列表，第一节的进阶。
 */

public class Advanced1Fragment extends BaseFragment {

    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_advanced1, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_advanced);
        ArrayList<View> views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.advanced2_custom, null, false));
        views.add(inflater.inflate(R.layout.advanced1_custom, null, false));
        viewPager.setAdapter(new MyPagerAdapter(views));
        return view;
    }


    class MyPagerAdapter extends PagerAdapter {

        private ArrayList<View> viewLists;

        public MyPagerAdapter(ArrayList<View> lists) {
            super();
            viewLists = lists;
        }

        @Override
        public int getCount() {
            Logger.e("长度++" + viewLists.size());
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }
}
