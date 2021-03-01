package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by yzz on 2017/9/25.
 * 自定义视图，使用ObjectAnimator。就是感觉吊吊的。
 * 使用方式：
 * <p>
 * 1.如果是自定义控件，需要添加 setter / getter 方法；
 * 2.用 ObjectAnimator.ofXXX() 创建 ObjectAnimator 对象；
 * 3.用 start() 方法执行动画。
 */

public class Custom601View extends View {

    private float progress = 0;

    public Custom601View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF = new RectF(100, 100, 400, 400);
        canvas.drawArc(rectF, 135, progress * 2.7f, false, paint);
    }
}
