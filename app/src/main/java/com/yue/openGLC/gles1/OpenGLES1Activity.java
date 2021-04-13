package com.yue.openGLC.gles1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.yue.yueapp.R;


/**
 *Time: 2021/4/13
 *Author:yzzCool
 *Description: 主要是为了对应 字节流动博客，NDK OpenGL ES 3.0 开发（一）：绘制一个三角形
 */
public class OpenGLES1Activity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGLSurfaceView surfaceView=new MyGLSurfaceView(this);
        setContentView(surfaceView);

    }



}