package com.yue.opengl.deep1;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 *Time: 2020/10/10
 *Author:yzzCool
 *Description:博客1，实现简单的三角形绘制。
 * https://www.jianshu.com/p/4a014afde409
 */
public class Deep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_deep1);

        //创建一个GLSurfaceView
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);
        //设置自己的Render.Render 内进行图形的绘制
        glSurfaceView.setRenderer(new GLRenderer1());

        setContentView(glSurfaceView);
    }


}