package com.yue.uilibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2017/7/4.
 * <p>
 * PathMeasure的使用。PathMeasure是一个用来测试Path的类
 */

public class PathMeasureView extends View {
    private int mViewWidth, mViewHeight;

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ko, options);
        mMatrix = new Matrix();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

//        Path path = new Path();
//        path.lineTo(0, 200);
//        path.lineTo(200, 200);
//        path.lineTo(200, 0);
//        PathMeasure pathMeasure = new PathMeasure(path, false);
//        PathMeasure pathMeasure1 = new PathMeasure(path, true);
//        Log.e("TAG", "forceClosed=false---->" + pathMeasure.getLength());
//        Log.e("TAG", "forceClosed=true----->" + pathMeasure1.getLength());
//        canvas.drawPath(path, paint);
//
//        Path path = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        Path dst = new Path();
//        dst.lineTo(-300, -300);
//        PathMeasure measure = new PathMeasure(path, false);
//        measure.getSegment(200, 600, dst, false);
//        //结论就是被截取的path片段会添加到dst中，而不是替换dst中到内容。
//        //forceClosed的值对图形有影响，就是会不会去连接在一起（保证连续性）
//        canvas.drawPath(path, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawPath(dst, paint);
//

//        Path path = new Path();
//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        PathMeasure pathMeasure = new PathMeasure(path, false);
//        float length1 = pathMeasure.getLength();
//        Log.e("TAG", "length111+" + length1);
//        pathMeasure.nextContour();
//        float length2 = pathMeasure.getLength();
//        Log.e("TAG", "length222+" + length2);
//        pathMeasure.nextContour();
//        float length3 = pathMeasure.getLength();
//        Log.e("TAG", "length222+" + length3);
//canvas.translate(mViewWidth / 2, mViewHeight / 2);      // 平移坐标系

//        Path path = new Path();                                 // 创建 Path
//        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
//        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure
//        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
//        if (currentValue >= 1) {
//            currentValue = 0;
//        }
//        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
//        mMatrix.reset();                                                        // 重置Matrix
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片,中心做的旋转
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合，中心目前该到的位置
//        canvas.drawPath(path, paint);                                   // 绘制 Path
//        canvas.drawBitmap(mBitmap, mMatrix, paint);                     // 绘制箭头
//        invalidate();


    }
}
