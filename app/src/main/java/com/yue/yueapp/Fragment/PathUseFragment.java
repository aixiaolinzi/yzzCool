package com.yue.yueapp.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yue.uilibrary.BezierLoveView;
import com.yue.uilibrary.PathBezierView;
import com.yue.yueapp.R;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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


        radioGroup.setOnClickListener(view1 -> getActivity().finish());

        return view;
    }

    /**
     * 下面是lambda的使用，具体的使用参考博客
     *
     * @param <T>
     */
    //定义一个接口
    interface Comparator<T> {
        int compare(T var1, T var2);
    }

    //老的实现方法
    Comparator comparator = new Comparator<String>() {
        @Override
        public int compare(String var1, String var2) {
            return 0;
        }
    };
    //lambda优化一下
    Comparator<String> comparator1 = (String s1, String s2) -> {
        return 0;
    };
    //lambda最后的优化，当编译器可以推导出具体的参数类型时
    Comparator<String> comparator2 = (s1, s2) -> {
        return 0;
    };

    interface Function<T, R> {
        R applay(T t);
    }

    Function<Integer, String> function = new Function<Integer, String>() {
        @Override
        public String applay(Integer integer) {
            return String.valueOf(integer);
        }
    };
    Function<Integer, String> function1 = integer -> String.valueOf(integer);


    Predicate<String> predicate = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            // 下面的代码和TextUtils.isEmpty(s)等价。
            return s == null || s.length() == 0;
        }
    };
    //进一步用lambda简化得到
    Predicate<String> predicate1 = s -> TextUtils.isEmpty(s);
    //但是还是可以继续简化的，我的天啊！！！
    Predicate<String> predicate2 = String::isEmpty;

    interface BitFunction<T1, T2, R> {
        R applay(T1 t1, T2 t2);
    }

    BitFunction<String, String, Boolean> bitFunction = new BitFunction<String, String, Boolean>() {
        @Override
        public Boolean applay(String s, String s2) {


            return s.endsWith(s2);
        }
    };
    //使用lambda得到下面的结果
    BitFunction<String, String, Boolean> bitFunction1 = String::endsWith;

    BitFunction<List<String>, List<String>, Boolean> bitFunction3 = List::contains;


}
