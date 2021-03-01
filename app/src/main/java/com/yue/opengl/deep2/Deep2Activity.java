package com.yue.opengl.deep2;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Deep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_deep2);
        //创建一个GLSurfaceView
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);
        //设置自己的Render.Render 内进行图形的绘制
        glSurfaceView.setRenderer(new GLRenderer1(this));

        setContentView(glSurfaceView);
    }
}