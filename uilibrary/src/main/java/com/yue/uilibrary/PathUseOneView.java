package com.yue.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2017/6/29.
 * 初次使用path
 */

public class PathUseOneView extends View {

    private int width, height;


    public PathUseOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();

        canvas.translate(width / 2, height / 2);

        Log.e("TAG", "得到的宽度+" + width + "得到的高度+" + height);
        Log.e("TAG", "得到Measured的宽度" + getMeasuredWidth() + "得到的宽度+" + getWidth() + "得到的Measured高度+" + getMeasuredHeight() + "得到高度+" + getHeight());

        Paint mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);

//        Path path = new Path();                     // 创建Path
//        path.lineTo(200, 200);                      // lineTo
////        path.moveTo(200, 100);                       // moveTo
//        path.setLastPoint(200, 100);
//        path.lineTo(200, 0);                         // lineTo
//        path.close();
//        canvas.drawPath(path, mPaint);              // 绘制Path


//        Path path1 = new Path();
//        path1.addRect(-100, -100, 100, 100, Path.Direction.CCW);
//        path1.setLastPoint(-200, 200);
//        path1.addRect(-200,-200,200,200, Path.Direction.CCW);
//        canvas.drawPath(path1, mPaint);

//        //第二类 就是Path的合并。就是两个path合并为一个。
//        Path path = new Path();
//        Path path1 = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        path1.addCircle(0, 0, 100, Path.Direction.CW);
//        path.addPath(path1, 0, -200);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawPath(path, mPaint);

//        //第三类 addARC和ARCTo处理圆弧的问题。
//        canvas.scale(1, -1);//首先翻转x和y的坐标
//        Path path = new Path();
//        path.lineTo(100, 100);
//
//        RectF oval = new RectF(0, 0, 300, 300);
////        path.addRect(oval, Path.Direction.CCW);
////        path.addArc(oval, 0, 270);
////        path.arcTo(oval, 0, 270);
//        path.arcTo(oval, 0, 270, false);
//        canvas.drawPath(path, mPaint);


//        第三组 isEmpty，isRect，isConvex ,set和offset

//        Path path = new Path();
//        Log.e("TAG", path.isEmpty() + "++isEmpty");
//        //判断path是否是空
//        Log.e("TAG", path.isEmpty() + "++isEmpty");
//        path.lineTo(0, 200);
//        path.lineTo(200, 200);
//        path.lineTo(191, 0);
//        path.lineTo(0, 0);
//        RectF rectF = new RectF();
//        boolean isRect = path.isRect(rectF);
//        Log.e("TAG", isRect + "+left+" + rectF.left + "+right+" + rectF.right + "+top+" + rectF.top + "+bottom+" + rectF.bottom);
//
//        //set的使用：
//        Path path = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//
//        Path path1 = new Path();
//        path1.addCircle(0, 200, 100, Path.Direction.CW);
//        canvas.drawPath(path, mPaint);
//
//        path.set(path1);
//
//        canvas.drawPath(path, mPaint);
        //offset的使用。
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path path1 = new Path();
        path1.addCircle(0, 200, 100, Path.Direction.CW);

        path.offset(100, 100);

        canvas.drawPath(path, mPaint);

        canvas.drawPath(path1, mPaint);

    }
}
