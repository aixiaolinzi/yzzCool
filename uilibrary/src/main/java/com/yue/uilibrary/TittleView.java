package com.yue.uilibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

/**
 * Created by fengfan on 2017/5/24.
 */

public class TittleView extends FrameLayout {

    Button btn_title_back;
    private ViewTittleButton tittlebutton;

    public TittleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_back, this);

        btn_title_back = (Button) findViewById(R.id.btn_title_back);
        btn_title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tittlebutton.click();
            }
        });
    }

    public void setTittleButton(ViewTittleButton tittlebutton) {
        this.tittlebutton = tittlebutton;
    }


    public interface ViewTittleButton {
        void click();
    }


}
