package example.yzz.appactivity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.yzz.appactivity.R;

/**
 * 描述:
 * Created by yzz on 2017/12/14.
 */

@SuppressLint("ValidFragment")
public class CrashFragment extends Fragment {

    public CrashFragment(String tag) {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crash, container, false);
        return view;
    }
}
