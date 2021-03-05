package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.yue.uilibrary.R;

/**
 * Created by yzz on 2017/9/19.
 * HenCoder Android 开发进阶：自定义 View 1-4 Canvas 对绘制的辅助
 * <p>
 * Canvas 对绘制的辅助——范围裁切和几何变换。
 */

public class Custom401View extends View {

    public Custom401View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.matrix);
        //166*250
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        String text = "得到的图片的宽+" + width + "高度+" + height;


        Paint textPaint = new Paint();
        textPaint.setTextSize(50);
        canvas.drawText(text, 0, 1000, textPaint);

        /**
         * 1 范围裁切
         * 范围裁切有两个方法： clipRect() 和 clipPath()。
         */


        //1.1 clipRect()
//        canvas.save();
//        canvas.clipRect(0, 0, 90, 50);//就是把这一块剪切下来。
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

        //1.2 clipPath()
        //其实和 clipRect() 用法完全一样，只是把参数换成了 Path ，所以能裁切的形状更多一些。

//        Path path = new Path();
//        path.addCircle(84, 125, 84, Path.Direction.CW);
//
//        canvas.save();
//        canvas.clipPath(path);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();


        /**
         * 2 几何变换
         * 几何变换的使用大概分为三类：
         * 1.使用 Canvas 来做常见的二维变换；
         * 2.使用 Matrix 来做常见和不常见的二维变换；
         * 3.使用 Camera 来做三维变换。
         */


        //2.1 使用 Canvas 来做常见的二维变换：
        //2.1.1 Canvas.translate(float dx, float dy) 平移
        //参数里的 dx 和 dy 表示横向和纵向的位移。


        //2.1.2 Canvas.rotate(float degrees, float px, float py) 旋转
        //参数里的 degrees 是旋转角度，单位是度（也就是一周有 360° 的那个单位），
        //方向是顺时针为正向； px 和 py 是轴心的位置。


//        canvas.save();
//        canvas.rotate(90, 84, 125);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();


        //2.1.3 Canvas.scale(float sx, float sy, float px, float py) 放缩
        //参数里的 sx sy 是横向和纵向的放缩倍数； px py 是放缩的轴心。(不是图片的轴心啊，是坐标轴)

//        canvas.save();
//        canvas.scale(3f, 3f, 0, 0);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();


        //2.1.4 skew(float sx, float sy) 错切
        //参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数。
        //参数含义：
        //float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
        //float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
        // 变换后:
        //X = x + sx * y
        //Y = sy * x + y

//        canvas.save();
//        canvas.skew(0, 3f);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();


        //2.2 使用 Matrix 来做变换
        //2.2.1 使用 Matrix 来做常见变换
        /**
         * Matrix 做常见变换的方式：
         * 1.创建Matrix
         * 2.调用 Matrix 的 pre/postTranslate/Rotate/Scale/Skew() 方法来设置几何变换；
         * 3.使用 Canvas.setMatrix(matrix) 或 Canvas.concat(matrix) 来把几何变换应用到 Canvas。
         *
         * Matrix matrix = new Matrix();

         ...

         matrix.reset();
         matrix.postTranslate();
         matrix.postRotate();

         canvas.save();
         canvas.concat(matrix);
         canvas.drawBitmap(bitmap, x, y, paint);
         canvas.restore();

         效果和Canvas一样。

         注意：

         把 Matrix 应用到 Canvas 有两个方法： Canvas.setMatrix(matrix) 和 Canvas.concat(matrix)。

         1.Canvas.setMatrix(matrix)：用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，
         改用 Matrix 的变换（注：根据我在一些评论以及微信公众号中收到的反馈，
         不同的手机系统中 setMatrix(matrix) 的行为可能不一致，所以还是尽量用 concat(matrix) 吧）；
         2.Canvas.concat(matrix)：用 Canvas 当前的变换矩阵和 Matrix 相乘，
         即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。

         */


        //2.2.2 使用 Matrix 来做自定义变换
        //Matrix 的自定义变换使用的是 setPolyToPoly() 方法。
        //2.2.2.1 Matrix.setPolyToPoly(float[] src, int srcIndex, float[] dst,
        //                              int dstIndex, int pointCount) 用点对点映射的方式设置变换
        //
        // poly 就是「多」的意思。
        // setPolyToPoly() 的作用是通过多点的映射的方式来直接设置变换。
        // 「多点映射」的意思就是把指定的点移动到给出的位置，从而发生形变。
        // 例如：(0, 0) -> (100, 100) 表示把 (0, 0) 位置的像素移动到 (100, 100) 的位置，
        // 这个是单点的映射，单点映射可以实现平移。而多点的映射，就可以让绘制内容任意地扭曲。


        //继续分析：：
        //从参数我们可以了解到setPolyToPoly最多可以支持4个点，这四个点通常为图形的四个角，
        // 可以通过这四个角将视图从矩形变换成其他形状。
        //下面的实战中：
        // left的值直接影响的就是画布。
        // 图片在不同的位置，画布上显示的大小是不同的。
        // 但是图片的绘画地点是left和top那么图片类型，相对好处理。


//        float left = 20;
//        float top = 200;
//        float right = left + width;
//        float bottom = top + height;
//
//
//        float pointsSrc[] = {left, top,//左上
//                right, top,//右上
//                left, bottom,//
//                right, bottom
//        };
//        float pointsDst[] = {left, top,
//                right, top,
//                left, bottom,
//                right, bottom + 100};
//
//
//        canvas.drawBitmap(bitmap, 500, 500, paint);
//
//        Matrix matrix = new Matrix();
//        matrix.reset();
//        matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
//        canvas.save();
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap, 200, 200, paint);
//        canvas.restore();


        /**
         * 2.3 使用 Camera 来做三维变换
         * 注意：：：
         * 《看清楚是Camera，不是Canvas》
         *
         * 2.3.1 Camera.rotate*() 三维旋转
         * Camera.rotate*() 一共有四个方法：
         * rotateX(deg) rotateY(deg) rotateZ(deg)rotate(x, y, z)。
         */

        //

        float left = 250;
        canvas.save();
        canvas.clipRect(left, left, left + width, left + height / 2);//就是把这一块剪切下来。
        canvas.drawBitmap(bitmap, left, left, paint);
        canvas.restore();

        canvas.save();
        Camera myCamera = new Camera();


        myCamera.save();
        myCamera.rotateX(45); // 旋转 Camera 的三维空间
        canvas.translate(width / 2 + left, height / 2 + left); // 旋转之后把投影移动回来
        myCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-width / 2 - left, -height / 2 - left);
        myCamera.restore();


        canvas.clipRect(left, left + height / 2, left + width, left + height);

        canvas.drawBitmap(bitmap, left, left, paint);
        canvas.restore();


        //2.3.2 Camera.translate(float x, float y, float z) 移动
        //它的使用方式和 Camera.rotate*() 相同，而且我在项目中没有用过它，所以就不贴代码和效果图了。


        //2.3.3 Camera.setLocation(x, y, z) 设置虚拟相机的位置
        //注意！这个方法有点奇葩，它的参数的单位不是像素，而是 inch，英寸。
        //英寸和像素的换算单位在 Skia 中被写死为了 72 像素。
        //在 Camera 中，相机的默认位置是 (0, 0, -8)（英寸）。
        //8 x 72 = 576，所以它的默认位置是 (0, 0, -576)（像素）。


//        canvas.save();
//        Path path = new Path();
//        path.addCircle(width, height / 2, width / 2, Path.Direction.CW);
//        canvas.clipPath(path);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();



        canvas.save();
        Path path1 = new Path();


        path1.lineTo(0, height);
        path1.lineTo(width, height);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path1.arcTo(width/2, width/2, width+width/2, width+width/2, 90, 180, false);
        }
        path1.lineTo(0, 0);

//        canvas.drawPath(path1,paint);
        canvas.clipPath(path1);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

    }
}
