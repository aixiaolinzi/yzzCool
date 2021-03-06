package com.yue.opengl.deep2;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 *Time: 2021/4/19
 *Author:yzzCool
 *Description: Android OpenGL ES(二)-正交投影
 * 原来的路径已经找不到。 https://cloud.tencent.com/developer/article/1198762
 *
 */
public class Deep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建一个GLSurfaceView
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);
        //设置自己的Render.Render 内进行图形的绘制
//        glSurfaceView.setRenderer(new GLRenderer2(this));
        //带有不同颜色的正交矩阵
        glSurfaceView.setRenderer(new GLRendererColor2(this));

        setContentView(glSurfaceView);
    }
}