package com.yue.opengl.deep3;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 注意:
 * 因为我们是使用g20来进行编程，需要要注意导包
 *
 * 主要在Render类内，完成对应的绘制.
 * 对应的生命周期的回调
 * -> onSurfaceCreated
 * -> onSurfaceChanged
 * -> onDrawFrame
 *
 * Created by a2957 on 2018/5/3.
 */
public class ViewGLRender implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //0.简单的给窗口填充一种颜色
        GLES20.glClearColor(0.0f,0.0f,0.0f,0.0f);

        //在创建的时候，去创建这些着色器

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //在窗口改变的时候调用
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //0.glClear（）的唯一参数表示需要被清除的缓冲区。当前可写的颜色缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
