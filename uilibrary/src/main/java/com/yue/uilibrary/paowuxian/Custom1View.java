package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Binder;
import android.os.Build;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by yzz on 2017/8/30.
 */

public class Custom1View extends View {

    /**
     * 插播Paint的知识。
     * 1.Paint.setColor(int color)改变颜色。
     * 2.Paint.setStyle(Paint.Style style)绘制的类型
     * FILL 是填充模式，
     * STROKE 是画线模式（即勾边模式），
     * FILL_AND_STROKE 是两种模式一并使用：既画线又填充。
     * 它的默认值是 FILL，填充模式。
     * 3.Paint.setStrokeWidth(float width)线条的宽度
     * 4.抗锯齿
     * 开启抗锯齿很简单，只要在 new Paint() 的时候加上一个 ANTI_ALIAS_FLAG 参数就行：
     * Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
     * 另外，你也可以使用 Paint.setAntiAlias(boolean aa) 来动态开关抗锯齿。
     */
    Paint paint = new Paint();


    public Custom1View(Context context, @Nullable AttributeSet attrs) {
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
        // 1.绘制一个圆
        paint.setColor(Color.YELLOW);
        canvas.drawColor(Color.parseColor("#88880000"));
        canvas.drawCircle(300, 300, 200, paint);
        //注意：：上面的顺序对产生的图是有影响的。
        //1.Canvas.drawColor(@ColorInt int color) 颜色填充（改变的是整个蒙版）
        //drawColor(Color.BLACK) 会把整个区域染成纯黑色，覆盖掉原有内容；
        //drawColor(Color.parse("#88880000") 会在原有的绘制效果上加一层半透明的红色遮罩
        //drawRGB(int r, int g, int b) 和 drawARGB(int a, int r, int g, int b)与上面的类似

        //2.绘制矩形。
        canvas.drawRect(600, 600, 800, 800, paint);

        //画点
        // 点的大小可以通过 paint.setStrokeWidth(width) 来设置；
        // 点的形状可以通过 paint.setStrokeCap(cap) 来设置
        // 端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(30);
        canvas.drawPoint(400, 800, paint);

        //3.画批量的点
        float[] pts = new float[]{10, 10, 10, 29, 40, 50, 60, 40, 30, 59, 60, 120, 129, 349};
        canvas.drawPoints(pts, 1, 9, paint);
        //drawPoints(@Size(multiple=2) float[] pts, int offset, int count, @NonNull Paint paint) {
        //offset:跳过前面的几个。count：一共画几个。


        //4.画椭圆
        RectF rectF = new RectF(400, 50, 700, 200);
        canvas.drawOval(rectF, paint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(400, 50, 700, 200, paint);
            //版本号，大于等于21  才可以使用。
        }

        //5.画线
        canvas.drawLine(200, 300, 400, 800, paint);//分别是起点和重点的坐标
        //由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。


        //6.画线（批量）,,drawLines() 是 drawLine() 的复数版。
        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120,
                150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points, paint);


        //7.画圆角矩形
        //drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint)
        //left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
        canvas.drawRoundRect(rectF, 50, 50, paint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);


        //8.绘制弧形或扇形
        //drawArc() 是使用一个椭圆来描述弧形的。
        //drawArc(float left, float top, float right, float bottom,
        //        float startAngle, float sweepAngle, boolean useCenter, Paint paint)

        //startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
        // sweepAngle 是弧形划过的角度；
        // useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。

        paint.setStyle(Paint.Style.FILL); // 填充模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setColor(Color.BLUE);
            canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint); // 绘制扇形
            canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint); // 绘制弧形
            paint.setStyle(Paint.Style.STROKE); // 画线模式

            canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint); // 绘制不封口的弧形
        }


        //9.drawPath(Path path, Paint paint) 画自定义图形
        //drawPath(path) 这个方法是通过描述路径的方式来绘制图形的，
        // 它的 path 参数就是用来描述图形路径的对象。

        Path path = new Path();
        paint.setColor(Color.RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 使用 path 对图形进行描述（这段描述代码不必看懂）
            path.addArc(200, 200, 400, 400, -225, 225);
            path.arcTo(400, 200, 600, 400, -180, 225, false);
            path.lineTo(400, 542);
        } else {
            path.addArc(new RectF(200, 200, 400, 400), -225, 225);
            path.arcTo(new RectF(400, 200, 600, 400), -180, 225, false);
            path.lineTo(400, 542);
        }
        canvas.drawPath(path, paint);


        //10.Path 有两类方法，一类是直接描述路径的，另一类是辅助的设置或计算。
        //Path 方法第一类：直接描述路径。
        //第一组： addXxx() ——添加子图形.
        //addCircle(float x, float y, float radius, Direction dir) 添加圆
        //x, y, radius 这三个参数是圆的基本信息，最后一个参数 dir 是画圆的路径的方向。
        //路径方向有两种：顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise) 。

        paint.setColor(Color.GREEN);
        path.addCircle(600, 600, 500, Path.Direction.CW);

        canvas.drawPath(path, paint);
        //drawPath() 一般是在绘制组合图形时才会用到的。
        //addOval(float left, float top, float right, float bottom, Direction dir) / addOval(RectF oval, Direction dir) 添加椭圆
        //addRect(float left, float top, float right, float bottom, Direction dir) / addRect(RectF rect, Direction dir) 添加矩形
        //addRoundRect(RectF rect, float rx, float ry, Direction dir) / addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Direction dir) / addRoundRect(RectF rect, float[] radii, Direction dir) / addRoundRect(float left, float top, float right, float bottom, float[] radii, Direction dir) 添加圆角矩形
        //addPath(Path path) 添加另一个 Path
        //上面的操作是先添加，然后是画图。


        //第二组：xxxTo()---画线（直线或曲线）
        //这一组和上一组addXxx()的区别在于，第一组是添加的完整封闭图形（除了addPath()）
        //而这一组添加的只是一条线。

        //1).lineTo(float x, float y) / rLineTo(float x, float y) 画直线
        //从当前位置向目标位置画一条直线， x 和 y 是目标位置的坐标。
        //lineTo(x, y)的参数是绝对坐标，
        // 而 rLineTo(x, y) 的参数是相对当前位置的相对坐标 （前缀 r 指的就是 relatively 「相对地」)。
        //当前位置：所谓当前位置，即最后一次调用画 Path 的方法的终点位置。初始值为原点 (0, 0)。
        //paint.setStyle(Style.STROKE);
        //path.lineTo(100, 100); // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
        //path.rLineTo(100, 0); // 由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线


        //2).quadTo(float x1, float y1, float x2, float y2) / rQuadTo(float dx1, float dy1, float dx2, float dy2) 画二次贝塞尔曲线
        //这条二次贝塞尔曲线的起点就是当前位置，而参数中的 x1, y1 和 x2, y2 则分别是控制点和终点的坐标。


        //3).cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) / rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) 画三次贝塞尔曲线
        //三阶贝塞尔曲线和上面的异曲同工。


        //4).moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
        //不论是直线还是贝塞尔曲线，都是以当前位置作为起点，而不能指定起点。
        //但你可以通过 moveTo(x, y)或 rMoveTo() 来改变当前位置，从而间接地设置这些方法的起点。


        //5).arcTo() 和 addArc()。它们也是用来画线的，但并不使用当前位置作为弧线的起点。

        Path path1 = new Path();
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        path1.lineTo(100, 100);
        path1.arcTo(200, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
        canvas.drawPath(path1, paint);


        //6)close() 封闭当前子图形,就是让图形封闭
        Path path2 = new Path();
        paint.setStyle(Paint.Style.STROKE);
        path2.moveTo(100, 100);
        path2.lineTo(200, 100);
        path2.lineTo(150, 150);
        path2.close(); // 使用 close() 封闭子图形。等价于 path2.lineTo(100, 100)
        canvas.drawPath(path2, paint);


        //11.Path 有两类方法，一类是直接描述路径的，另一类是辅助的设置或计算。
        //Path 方法第二类：辅助的设置或计算。用的比较少。。
        // setFillType(FillType fillType)。
        //EVEN_ODD与WINDING （默认值）
        //INVERSE_EVEN_ODD与INVERSE_WINDING

        //简单粗暴，WINDING全填充，而 EVEN_ODD 是「交叉填充」。
        //注意：：要考虑方向，如果考虑方向那么WINDING全填充就不是这样玩了。。
        //分析：：
        // even-odd rule （奇偶原则）：对于平面中的任意一点，向任意方向射出一条射线，
        // 这条射线和图形相交的次数（相交才算，相切不算哦）如果是奇数，则这个点被认为在图形内部，
        // 是要被涂色的区域；如果是偶数，则这个点被认为在图形外部，是不被涂色的区域。
        //即 non-zero winding rule （非零环绕数原则）：首先，它需要你图形中的所有线条都是有绘制方向的：
        //然后，同样是从平面中的点向任意方向射出一条射线，但计算规则不一样：以 0 为初始值，
        // 对于射线和图形的所有交点，遇到每个顺时针的交点（图形从射线的左边向右穿过）把结果加 1，
        // 遇到每个逆时针的交点（图形从射线的右边向左穿过）把结果减 1，
        // 最终把所有的交点都算上，得到的结果如果不是 0，则认为这个点在图形内部，是要被涂色的区域；
        // 如果是 0，则认为这个点在图形外部，是不被涂色的区域。
        //分析就是：0是外部不填充，非0是内部填充。顺时针加1，逆时针减1。

        //12.drawBitmap
        //绘制 Bitmap 对象，也就是把这个 Bitmap 中的像素内容贴过来。



        //13.drawText(String text, float x, float y, Paint paint) 绘制文字
        //界面里所有的显示内容，都是绘制出来的，包括文字。 drawText() 这个方法就是用来绘制文字的。
        //Paint.setTextSize(float textSize)文字的大小可以设置。




    }

}
