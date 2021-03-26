package com.yue.opengl.matrix;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.yue.opengl.view.MatrixLearnView;
import com.yue.yueapp.R;


/**
 * Time: 2021/3/2YzzLogger
 * Author:yzzCool
 * Description: 对应博客 Android Matrix 小整理
 */
public class Matrix1Activity extends AppCompatActivity {
    private MatrixLearnView matrix_learn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl_matrix);

        matrix_learn_view = findViewById(R.id.matrix_learn_view);
    }


    public void matrix1(View view) {
        //展示原图
        matrix_learn_view.showOriginalPicture();
    }

    public void matrix2(View view) {
        //缩放（Scale）的使用
        matrix_learn_view.showSetScale();
    }


    public void matrix3(View view) {
        //位移（Translate）的使用
        matrix_learn_view.showSetTranslate();
    }

    public void matrix4(View view) {
        //错切（Skew）的使用
        matrix_learn_view.showSetSkew();
    }


    public void matrix5(View view) {
        //旋转（Rotate）的使用
        matrix_learn_view.showSetRotate();
    }

    public void matrix6(View view) {
        //串连接（Concat）的使用
        matrix_learn_view.showSetConcat();
    }


    public void matrix7(View view) {
        //正弦余弦的使用
        matrix_learn_view.showSetSinCos();
    }
}