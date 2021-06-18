package com.yue.opengl.deep4;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


 /**
  *Time: 2021/6/18
  *Author:yzzCool
  *Description:  Android OpenGL ES(四)-为平面图添加滤镜
  * https://www.jianshu.com/p/7bcdb68823cb
  */
public class Deep4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建一个GLSurfaceView
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);

        glSurfaceView.setEGLContextClientVersion(2);

        //带有不同颜色的正交矩阵
        glSurfaceView.setRenderer(new GLRendererTextureFilter4(this));


        setContentView(glSurfaceView);
    }
}