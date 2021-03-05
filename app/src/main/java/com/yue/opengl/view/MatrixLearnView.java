package com.yue.opengl.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.yue.yueapp.R;
import com.yue.yueapp.utils.LoggerUtils;

import java.util.Arrays;

/**
 * Time:2021/3/2
 * Author:yzzCool
 * Description:
 */
public class MatrixLearnView extends View {
    private Bitmap bitmap;
    private Matrix matrix;
    private Paint paint;
    private String matrixValues;//具体的矩阵值


    public MatrixLearnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initValue();
    }

    /**
     * 初始化。
     */
    private void initValue() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.matrix);
        matrix = new Matrix();

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        getMatrixValues();//init
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, matrix, paint);


        canvas.drawText(matrixValues, 0, 751, paint);
    }

    //展示原始图片
    public void showOriginalPicture() {
        matrix.reset();
        getMatrixValues();//reset
    }

    //setScale
    public void showSetScale() {
//        matrix.setScale(0.5f, 0.5f);
        matrix.setScale(0.5f, 0.5f, 100f, 200f);
        getMatrixValues();//showSetScale
    }

    //setTranslate
    public void showSetTranslate() {
        matrix.setTranslate(100f, 50f);
        getMatrixValues();//showSetScale
    }


    //setSkew
    public void showSetSkew() {
        matrix.setSkew(1f, 0f);
        getMatrixValues();//showSetScale
    }

    //setRotate
    public void showSetRotate() {
        matrix.setRotate(30);
        getMatrixValues();//showSetScale
    }


    //setConcat
    public void showSetConcat() {
        Matrix matrix1 = new Matrix();
        matrix1.setTranslate(100, 100);

        Matrix matrix2 = new Matrix();
        matrix2.setScale(50, 200);

        matrix.setConcat(matrix1, matrix2);
        getMatrixValues();//showSetScale
    }

    //setSinCos
    public void showSetSinCos() {
        matrix.setSinCos(3f, 1);
        getMatrixValues();//showSetScale
    }

    private void getMatrixValues() {
        float[] values = new float[9];
        matrix.getValues(values);
        matrixValues = toMatrixValues(values);
        LoggerUtils.i("float[] values " + matrixValues);


        postInvalidate();
    }


    /**
     * 拼接为相应的value值
     *
     * @param values 数组
     * @return 返回一个字符串
     */
    public static String toMatrixValues(float[] values) {
        if (values == null)
            return "null";

        int iMax = values.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append("\n");
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(values[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
            if (i == 2) {
                b.append("\n ");
            }
            if (i == 5) {
                b.append("\n ");
            }
        }
    }
}
