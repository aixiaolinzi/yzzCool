package com.yue.opengl.deep3;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 *Time: 2021/4/21
 *Author:yzzCool
 *Description: Android OpenGL ES(三)-平面图形
 * https://www.jianshu.com/p/320980800358
 *
 */
public class Deep3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建一个GLSurfaceView
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);
        //正方形的绘制
        glSurfaceView.setRenderer(new GLRendererSquare3(this));
        //带有不同颜色的正交矩阵
//        glSurfaceView.setRenderer(new GLRendererColor2(this));

        setContentView(glSurfaceView);
    }
}