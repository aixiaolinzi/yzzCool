package com.yue.openGLC;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yue.openGLC.gles1.OpenGLES1Activity;
import com.yue.openGLC.guide1.OpenGLGuide1Activity;
import com.yue.yueapp.R;



/**
 *Time: 2021/4/2
 *Author:yzzCool
 *Description:
 */
public class OpenGLESMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_open_gl_es);
    }

    public void flow1(View view) {
        //NDK OpenGL ES 3.0 开发（一）：绘制一个三角形
        startActivity(new Intent(this, OpenGLES1Activity.class));
    }

    public void guide1(View view) {

        //NDK OpenGL ES 3.0 开发（一）：绘制一个三角形
        startActivity(new Intent(this, OpenGLGuide1Activity.class));
    }
    public void guide2(View view) {

    }
}
