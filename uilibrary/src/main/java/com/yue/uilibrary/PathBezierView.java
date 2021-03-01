package com.yue.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2017/6/29.
 * 贝塞尔曲线的使用
 */

public class PathBezierView extends View {
    private Paint mPaint;
    private int centerX, centerY;//就是找到中间的点

    private PointF start, end, controlLeft, controlRight;

    private boolean isRightMove;

    public PathBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        controlLeft = new PointF(0, 0);
        controlRight = new PointF(0, 0);
        isRightMove = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("TAG", "得到centerX的值" + centerX + "得到centerY的值+" + centerY);

        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;

        //控制点的初始位置
        controlLeft.x = centerX - 100;
        controlLeft.y = centerY - 100;

        controlRight.x = centerX + 100;
        controlRight.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRightMove) {
            controlRight.x = event.getX();
            controlRight.y = event.getY();
        } else {
            controlLeft.x = event.getX();
            controlLeft.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(controlLeft.x, controlLeft.y, mPaint);
        canvas.drawPoint(controlRight.x, controlRight.y, mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        //画线，辅助线
        canvas.drawLine(start.x, start.y, controlLeft.x, controlLeft.y, mPaint);
        canvas.drawLine(controlLeft.x, controlLeft.y, controlRight.x, controlRight.y, mPaint);
        canvas.drawLine(controlRight.x, controlRight.y, end.x, end.y, mPaint);


        mPaint.setColor(Color.YELLOW);
        //画线，贝塞尔曲线
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(controlLeft.x, controlLeft.y, controlRight.x, controlRight.y, end.x, end.y);

        canvas.drawPath(path, mPaint);


    }

    public void setRightMove(boolean rightMove) {
        this.isRightMove = rightMove;
    }
}
