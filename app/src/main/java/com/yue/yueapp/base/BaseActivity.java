package com.yue.yueapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yue.yueapp.Constant;
import com.yue.yueapp.utils.SettingUtil;

/**
 * 描述:
 * Created by yzz on 2017/12/12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private int iconType = -1;

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化滑动返回
     */
    protected void initSlidable() {
        int isSlidable = SettingUtil.getInstance().getSlidable();
        if (isSlidable != Constant.SLIDABLE_DISABLE) {
            //没有导入类库
//            SlidrConfig config = new SlidrConfig.Builder()
//                    .edge(isSlidable == Constant.SLIDABLE_EDGE)
//                    .build();
//            slidrInterface = Slidr.attach(this, config);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.iconType = SettingUtil.getInstance().getCustomIconValue();
        initSlidable();
    }


}
