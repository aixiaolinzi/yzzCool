package com.yue.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fengfan on 2017/5/24.
 * 数字自己会加的view
 */

public class CounterView extends View implements View.OnClickListener {
    private Paint mPaint;
    private Rect mBounds;
    private int mCount;

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();

        setOnClickListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 120, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(new RectF(0, 0, getWidth(), getWidth()), 0, 60, true, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(new RectF(0, 0, getWidth(), getWidth()), 60, 120, true, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(new RectF(0, 0, getWidth(), getWidth()), 180, 90, true, mPaint);


        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);

    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
