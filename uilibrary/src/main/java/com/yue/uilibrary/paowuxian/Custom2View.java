package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yzz on 2017/8/30.
 */

public class Custom2View extends View {

    /**
     * 对于没有深入研究过 Paint 的人，这期是一个对 Paint 的诠释。
     * Paint 的 API 大致可以分为 4 类：
     * 颜色
     * 效果
     * drawText() 相关
     * 初始化
     * <p>
     * 下面我就对这 4 类分别进行介绍：
     */
    Paint paint = new Paint();


    public Custom2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 1111111111
     * 一切的开始
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1 颜色
        //1.1基本颜色
        //Canvas 的颜色填充类方法
        //drawColor/RGB/ARGB() 的颜色，是直接写在方法的参数里，通过参数来设置的（上期讲过了）；
        //drawBitmap() 的颜色，是直接由 Bitmap 对象来提供的（上期也讲过了）；
        //除此之外，是图形和文字的绘制，它们的颜色就需要使用 paint 参数来额外设置了（下面要讲的）。

        //1.1.1直接设置颜色
        //1.1.1.1  setColor(int color)
        //paint.setColor(Color.parseColor("#009688"));
        //1.1.1.2 setARGB(int a, int r, int g, int b)
        //其实和 setColor(color) 都是一样一样儿的，只是它的参数用的是更直接的三原色与透明度的值。

        //1.1.2 setShader(Shader shader) 设置 Shader。着色器。
        //1.1.2.1 LinearGradient 线性渐变






    }

}
