package com.yue.yueapp.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yue.uilibrary.BezierLoveView;
import com.yue.uilibrary.PathBezierView;
import com.yue.yueapp.R;

/**
 * Created by Administrator on 2017/6/28.
 */

public class PathUseFragment extends Fragment {
    private RadioButton radioButtonLeft, radioButtonRight;
    private RadioGroup radioGroup;
    private BezierLoveView bezierView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.path_view_layout, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio);
        bezierView = (BezierLoveView) view.findViewById(R.id.bezier);
        ((RadioButton) view.findViewById(R.id.radio_left)).setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radio_left:
//                        bezierView.setRightMove(false);
                        Log.e("TAG", "left");
                        break;
                    case R.id.radio_right:
//                        bezierView.setRightMove(true);
                        Log.e("TAG", "right");
                        break;
                }
            }
        });

        return view;
    }
}
