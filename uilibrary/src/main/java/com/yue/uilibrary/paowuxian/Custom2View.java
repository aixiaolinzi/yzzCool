package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yue.uilibrary.R;


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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1.基本颜色的设置（ setColor/ARGB(), setShader() ）
         */
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
        // 注意：在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用。
        //TileMode 一共有 3 个值可选： CLAMP, MIRROR 和 REPEAT。
        // CLAMP会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。

        Shader shader = new LinearGradient(500, 100, 100, 100,
                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
        //分析：
        // LinearGradient(float x0, float y0, float x1, float y1,
        //                int color0,int color1, Shader.TileMode tile)。
        // x0 y0 x1 y1：渐变的两个端点的位置 color0 color1 是端点的颜色
        // tile：端点范围之外的着色规则，类型是 TileMode。
        //注意：x0 y0 x1 y1是渐变的范围对，真正画的图是有影响的。在什么范围内是什么颜色。
        //颜色是从x0->x1渐变，y0->y1渐变。这是一个好玩意。

        //1.1.2.2 RadialGradient 辐射渐变
        //辐射渐变很好理解，就是从中心向周围辐射状的渐变。

        Shader shader1 = new RadialGradient(300, 300, 200,
                Color.parseColor("#77887777"), Color.parseColor("#56565656"), Shader.TileMode.CLAMP);

        //1.1.2.3 SweepGradient 扫描渐变
        //长的就像是圆锥。
        //只需要扫描中心，起始颜色和终止颜色。
        Shader shader2 = new SweepGradient(300, 500,
                Color.parseColor("#213467aa"), Color.parseColor("#34343434"));


        //1.1.2.4 BitmapShader
        //用 Bitmap 来着色（终于不是渐变了）。其实也就是用 Bitmap 的像素来作为图形或文字的填充。
        //参数： bitmap：用来做模板的 Bitmap 对象
        // tileX：横向的 TileMode
        // tileY：纵向的 TileMode。

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.panjunnn);
        Shader shader3 = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader3);


        //1.1.2.5 ComposeShader 混合着色器
        //所谓混合，就是把两个 Shader 一起使用。

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.look);
        Shader shader4 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.leaf);
        Shader shader5 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Shader shader6 = new ComposeShader(shader4, shader5, PorterDuff.Mode.DST_OUT);
        paint.setShader(shader6);
//        canvas.drawRect(0, 0, 1080, 1920, paint);

        //PorterDuff.Mode的分析：两个Shader的组合问题。
        //PorterDuff.Mode 一共有 17 个。
        //分为两类：：
        //1.Alpha 合成 (Alpha Compositing)
        //2.混合 (Blending)，继续了解，就看0901的有道云笔记。


        /**
         * 2.基于原始颜色的过滤（ setColorFilter() ）
         */
        //1.2 setColorFilter(ColorFilter colorFilter)
        //ColorFilter 这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。
        // 颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，
        // 然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来。

        //在 Paint 里设置 ColorFilter ，使用的是 Paint.setColorFilter(ColorFilter filter)方法。
        // ColorFilter 并不直接使用，而是使用它的子类。
        // 它共有三个子类：LightingColorFilter PorterDuffColorFilter 和 ColorMatrixColorFilter。

        //1.2.1 LightingColorFilter
        //这个 LightingColorFilter 是用来模拟简单的光照效果的。
        //LightingColorFilter 的构造方法是 LightingColorFilter(int mul, int add) ，
        // 参数里的 mul 和 add 都是和颜色值格式相同的 int 值，其中 mul 用来和目标像素相乘，
        // add 用来和目标像素相加：
        //R' = R * mul.R / 0xff + add.R
        //G' = G * mul.G / 0xff + add.G
        //B' = B * mul.B / 0xff + add.B
        //取出相应的颜色进行乘法和加法。。。

        ColorFilter LightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        paint.setColorFilter(LightingColorFilter);
        canvas.drawRect(0, 0, 1080, 1920, paint);

        //1.2.2 PorterDuffColorFilter
        //这个 PorterDuffColorFilter 的作用是使用一个指定的颜色和一种指定的 PorterDuff.Mode 来与绘制对象进行合成。
        //它的构造方法是 PorterDuffColorFilter(int color, PorterDuff.Mode mode)
        // 其中的 color 参数是指定的颜色， mode 参数是指定的 Mode。


        //1.2.3 ColorMatrixColorFilter
        //ColorMatrixColorFilter 使用一个 ColorMatrix 来对颜色进行处理。
        //ColorMatrix 这个类，内部是一个 4x5 的矩阵：
        //[ a, b, c, d, e,
        //  f, g, h, i, j,
        //  k, l, m, n, o,
        //  p, q, r, s, t ]

        //对于颜色 [R, G, B, A] ，转换算法是这样的：
        //R’ = a*R + b*G + c*B + d*A + e;
        //G’ = f*R + g*G + h*B + i*A + j;
        //B’ = k*R + l*G + m*B + n*A + o;
        //A’ = p*R + q*G + r*B + s*A + t;


        /**
         * 3.setXfermode(Xfermode xfermode)，它处理的是「当颜色遇上 View」的问题。
         */
        //1.3 "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用的简写方式。
        //最后就是PorterDuffXfermode的使用。


        //下面是大概流程：
        //Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        // ...
        //canvas.drawBitmap(rectBitmap, 0, 0, paint); // 画方
        //paint.setXfermode(xfermode); // 设置 Xfermode
        //canvas.drawBitmap(circleBitmap, 0, 0, paint); // 画圆
        //paint.setXfermode(null); // 用完及时清除 Xfermode

        //一层一层的向下画的。注意事项
        //1. 使用离屏缓冲（Off-screen Buffer），就是两个图形都处理完成后才能投屏幕。
        //这样额可以防止出现一些不必要的错误。
        //(1).Canvas.saveLayer()
        //canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);
        //(2).View.setLayerType() 是直接把整个 View 都绘制在离屏缓冲中。
        // setLayerType(LAYER_TYPE_HARDWARE) 是使用 GPU 来缓冲，
        // setLayerType(LAYER_TYPE_SOFTWARE) 是直接直接用一个 Bitmap 来缓冲。
        //2. 控制好透明区域
        //还应该注意控制它的透明区域不要太小，要让它足够覆盖到要和它结合绘制的内容，否则得到的结果很可能不是你想要的。





    }

}
