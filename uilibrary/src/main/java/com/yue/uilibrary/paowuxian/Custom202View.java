package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


/**
 * Created by yzz on 2017/8/30.
 */

public class Custom202View extends View {

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


    public Custom202View(Context context, @Nullable AttributeSet attrs) {
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
         * 2.效果
         */

        //2.1 setAntiAlias (boolean aa) 设置抗锯齿
        paint.setAntiAlias(true);//开启
        //Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //或者是新建的时候。直接给这设置。

        //2.2 setStyle(Paint.Style style)
        //用来设置图形是线条风格还是填充风格的（也可以二者并用）：
        //FILL 模式，填充
        //STROKE 模式，画线
        //FILL_AND_STROKE 模式，填充 + 画线


        //2.3 线条形状
        //setStrokeWidth(float width),线条宽度。
        //setStrokeCap(Paint.Cap cap),设置线头的形状。BUTT 平头（默认）、ROUND 圆头、SQUARE 方头。
        //setStrokeJoin(Paint.Join join),设置拐角的形状。MITER 尖角（默认）、 BEVEL 平角和 ROUND 圆角
        //setStrokeMiter(float miter) 。它用于设置 MITER 型拐角的延长线的最大值。


        //2.4 色彩优化
        //setDither(boolean dither) 和 setFilterBitmap(boolean filter) 。
        //2.4.1 setDither(boolean dither)  设置图像的抖动。
        //在图像中有意地插入噪点，通过有规律地扰乱图像来让图像对于肉眼更加真实的做法。
        //看着很牛逼对吧？确实很牛逼，而且在 Android 里使用起来也很简单，一行代码就搞定：
        paint.setDither(true);

        //2.4.2 setFilterBitmap(boolean filter)  设置是否使用双线性过滤来绘制 Bitmap 。
        //图像在放大绘制的时候，默认使用的是最近邻插值过滤，这种算法简单，但会出现马赛克现象；
        //而如果开启了双线性过滤，就可以让结果图像显得更加平滑。
        paint.setFilterBitmap(true);


        //2.5 setPathEffect(PathEffect effect) 使用 PathEffect 来给图形的轮廓设置效果。
        //原本是应该画线的，但是设置上面的属性，可以是拐角圆角，随机偏离，虚线，三角代替等等。

        //2.5.1 CornerPathEffect  把所有拐角变成圆角。
        PathEffect pathEffect = new CornerPathEffect(20);
        paint.setPathEffect(pathEffect);


        //2.5.2 DiscretePathEffect 把线条进行随机的偏离，让轮廓变得乱七八糟.
        //float segmentLength  段长
        // float deviation   偏离
        PathEffect pathEffect1 = new DiscretePathEffect(20, 30);
        paint.setPathEffect(pathEffect1);


        //2.5.3 DashPathEffect
        //PathEffect pathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        // 第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），
        // 按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
        //例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
        // 第二个参数 phase 是虚线的偏移量。


        //2.5.4 PathDashPathEffect
        //Path dashPath = ...; // 使用一个三角形来做 dash
        //PathEffect pathEffect = new PathDashPathEffect(dashPath, 40, 0,PathDashPathEffectStyle.TRANSLATE);
        //paint.setPathEffect(pathEffect);
        //原图形就会是一堆的小三角形处理。
        //分析：
        //PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style)中，
        //shape 参数是用来绘制的 Path ；
        // advance 是两个相邻的 shape 段之间的间隔，不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
        // phase 和 DashPathEffect 中一样，是虚线的偏移；
        // 最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。
        //style 的类型为 PathDashPathEffect.Style ，是一个 enum ，具体有三个值：
        //TRANSLATE：位移
        //ROTATE：旋转
        //MORPH：变体


        //2.5.5 SumPathEffect
        //这是一个组合效果类的 PathEffect 。它的行为特别简单，就是分别按照两种 PathEffect分别对目标进行绘制。
        //PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        //PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        //pathEffect = new SumPathEffect(dashEffect, discreteEffect);
        //canvas.drawPath(path, paint);


        //2.5.6 ComposePathEffect
        //这也是一个组合效果类的 PathEffect 。
        //不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        //PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        //PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        //pathEffect = new ComposePathEffect(dashEffect, discreteEffect);
        //分析问题：：
        //它的构造方法 ComposePathEffect(PathEffect outerpe, PathEffect innerpe) 中的两个 PathEffect 参数，
        // innerpe 是先应用的， outerpe 是后应用的。所以上面的代码就是「先偏离，再变虚线」。
        //上面这些就是 Paint 中的 6 种 PathEffect。它们有的是有独立效果的，有的是用来组合不同的 PathEffect 的，功能各不一样。
        //注意： PathEffect 在有些情况下不支持硬件加速，需要关闭硬件加速才能正常使用：
        //1.Canvas.drawLine() 和 Canvas.drawLines() 方法画直线时，setPathEffect()是不支持硬件加速的；
        //2.PathDashPathEffect 对硬件加速的支持也有问题，所以当使用 PathDashPathEffect 的时候，最好也把硬件加速关了。


        /**
         * 剩下的两个效果类方法：setShadowLayer() 和 setMaskFilter() ，
         * 它们和前面的效果类方法有点不一样：它们设置的是「附加效果」，也就是基于在绘制内容的额外效果。
         */

        //2.6 setShadowLayer(float radius, float dx, float dy, int shadowColor)
        //在之后的绘制内容下面加一层阴影。
        //paint.setShadowLayer(10, 0, 0, Color.RED);
        //效果就是上面这样。方法的参数里， radius 是阴影的模糊范围； dx dy 是阴影的偏移量；
        // shadowColor 是阴影的颜色。
        //如果要清除阴影层，使用 clearShadowLayer() 。

        //注意：
        //1.在硬件加速开启的情况下， setShadowLayer() 只支持文字的绘制，
        // 文字之外的绘制必须关闭硬件加速才能正常绘制阴影。
        //2.如果 shadowColor 是半透明的，阴影的透明度就使用 shadowColor 自己的透明度；
        // 而如果 shadowColor 是不透明的，阴影的透明度就使用 paint 的透明度。


        //2.7 setMaskFilter(MaskFilter maskfilter)
        //为之后的绘制设置 MaskFilter。上一个方法 setShadowLayer() 是设置的在绘制层下方的附加效果；
        //而这个 MaskFilter 和它相反，设置的是在绘制层上方的附加效果。
        //到现在已经有两个 setXxxFilter(filter) 了。前面有一个 setColorFilter(filter)，是对每个像素的颜色进行过滤；
        //而这里的 setMaskFilter(filter) 则是基于整个画面来进行过滤。


        //2.7.1 BlurMaskFilter
        //模糊效果的 MaskFilter。
        //paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        //它的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中，
        // radius 参数是模糊的范围， style 是模糊的类型。
        // 一共有四种：
        //NORMAL: 内外都模糊绘制
        //SOLID: 内部正常绘制，外部模糊
        //INNER: 内部模糊，外部不绘制
        //OUTER: 内部不绘制，外部模糊（什么鬼？）


        //2.7.2 EmbossMaskFilter
        //浮雕效果的 MaskFilter。
        //paint.setShadowLayer(10, 0, 0, Color.RED);
        //它的构造方法 EmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius)的参数里，
        // direction 是一个 3 个元素的数组，指定了光源的方向；
        // ambient 是环境光的强度，数值范围是 0 到 1；
        // specular 是炫光的系数；
        // blurRadius 是应用光线的范围。


        //2.8获取绘制的 Path
        //也是效果类唯一的一组 get 方法，计算出绘制 Path 或文字时的实际 Path。

        //2.8.1 getFillPath(Path src, Path dst)
        //首先解答第一个问题：「实际 Path」。
        //所谓实际 Path ，指的就是 drawPath() 的绘制内容的轮廓，要算上线条宽度和设置的 PathEffect。

        //分析：：
        //默认情况下（线条宽度为 0、没有 PathEffect），原 Path 和实际 Path 是一样的；
        // 而在线条宽度不为 0 （并且模式为 STROKE 模式或 FLL_AND_STROKE ），
        // 或者设置了 PathEffect的时候，实际 Path 就和原 Path 不一样了：


        //2.8.2 文字的 Path。其实文字的path也是图形。
        // getTextPath(String text, int start, int end, float x, float y, Path path)
        // getTextPath(char[] text, int index, int count, float x, float y, Path path)
        //这里就回答第二个问题：「文字的 Path」。
        // 文字的绘制，虽然是使用 Canvas.drawText()方法，但其实在下层，文字信息全是被转化成图形，对图形进行绘制的。
        // getTextPath() 方法，获取的就是目标文字所对应的 Path 。这个就是所谓「文字的 Path」。
        //这两个方法， getFillPath() 和 getTextPath() ，就是获取绘制的 Path 的方法。


        /**
         * 4 初始化类
         * 4.1 reset()
         重置 Paint 的所有属性为默认值。相当于重新 new 一个，不过性能当然高一些啦。


         4.2 set(Paint src)
         把 src 的所有属性全部复制过来。相当于调用 src 所有的 get 方法，然后调用这个 Paint的对应的 set 方法来设置它们。


         4.3 setFlags(int flags)
         批量设置 flags。相当于依次调用它们的 set 方法。例如：
         paint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
         这行代码，和下面这两行是等价的：
         paint.setAntiAlias(true);
         paint.setDither(true);

         setFlags(flags) 对应的 get 方法是 int getFlags()。
         好了，这些就是 Paint 的四类方法：颜色类、效果类、文字绘制相关以及初始化类。
         其中颜色类、效果类和初始化类都已经在这节里面讲过了，剩下的一类——文字绘制类，下一节单独讲。
         */


    }

}
