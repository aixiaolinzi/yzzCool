package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by yzz on 2017/9/20.
 */

public class Custom501TextView extends android.support.v7.widget.AppCompatTextView {
    public Custom501TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#0000ff"));
        canvas.drawRect(0,0,500,500,paint);
    }
}
