package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by yzz on 2017/9/20.
 */

public class Custom501EditText extends AppCompatEditText {
    public Custom501EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 100, paint);
        canvas.drawCircle(400, 400, 100, paint);
        canvas.drawText("开始", 200, 200, paint);
    }
}
