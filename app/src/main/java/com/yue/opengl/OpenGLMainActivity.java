package com.yue.opengl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.yue.opengl.deep1.Deep1Activity;
import com.yue.opengl.deep2.Deep2Activity;
import com.yue.opengl.matrix.Matrix1Activity;
import com.yue.yueapp.R;
import com.yue.yueapp.utils.LoggerUtils;

import java.lang.ref.WeakReference;


/**
 *Time: 2021/3/1
 *Author:yzzCool
 *Description:  OpenGL的主布局
 */
public class OpenGLMainActivity extends AppCompatActivity {


    private static class MyHandler extends Handler {
        private final WeakReference<OpenGLMainActivity> activityWeakReference;

        private MyHandler(OpenGLMainActivity activity) {
            activityWeakReference = new WeakReference<OpenGLMainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoggerUtils.e("TAG", msg.what + "得到的值");
        }
    }


    private final MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_open_gl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myHandler.sendEmptyMessageAtTime(2, 5000);


    }



    public void matrix1(View view) {
        //Android Matrix 小整理
        startActivity(new Intent(this, Matrix1Activity.class));
    }


    public void deep1(View view) {
        startActivity(new Intent(this, Deep1Activity.class));
    }


    public void deep2(View view) {
        startActivity(new Intent(this, Deep2Activity.class));
    }


}
