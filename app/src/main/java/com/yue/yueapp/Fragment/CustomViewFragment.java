package com.yue.yueapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yue.uilibrary.CircularView;
import com.yue.uilibrary.MyListView;
import com.yue.uilibrary.TittleView;
import com.yue.yueapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengfan on 2017/5/24.
 * 最基本的fragment
 */

public class CustomViewFragment extends BaseFragment {
    Context mContext;

    CircularView circularView;
    ImageView imageView;
    TittleView tittle_view;
    MyListView myListView;
    MyAdapter myAdapter;

    private List<String> contentList = new ArrayList<String>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_layout, container, false);
        circularView = (CircularView) view.findViewById(R.id.wave_view);
        imageView = (ImageView) view.findViewById(R.id.image_count);
        tittle_view = (TittleView) view.findViewById(R.id.tittle_view);

        initList();
        myListView = (MyListView) view.findViewById(R.id.myList);
        myAdapter = new MyAdapter(mContext, 0, contentList);
        myListView.setAdapter(myAdapter);
        myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                myAdapter.notifyDataSetChanged();
            }
        });


        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;

        circularView.setOnWaveAnimationListener(new CircularView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                imageView.setLayoutParams(lp);
            }
        });

        tittle_view.setTittleButton(new TittleView.ViewTittleButton() {
            @Override
            public void click() {
                Log.e("Tag", "点击，点击。。");
            }
        });


        return view;
    }

    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item, null);
            } else {
                view = convertView;
            }
            TextView textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(getItem(position));
            return view;
        }
    }

    private void initList() {
        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
    }

}
