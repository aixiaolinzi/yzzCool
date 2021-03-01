package com.yue.opengl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.yue.yueapp.R;

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
            Log.e("TAG", msg.what + "得到的值");
        }
    }


    private final MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String mm = getIntent().getStringExtra("kkkk");
        Log.e("TAG", "得到值+" + mm);

        myHandler.sendEmptyMessageAtTime(2, 5000);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
