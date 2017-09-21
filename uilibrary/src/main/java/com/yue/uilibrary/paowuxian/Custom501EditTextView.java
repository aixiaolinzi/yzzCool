package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yzz on 2017/9/20.
 *
 * 2.2.7 光标相关
 * 对于 EditText 以及类似的场景，会需要绘制光标。光标的计算很麻烦，
 * 不过 API 23 引入了两个新的方法，有了这两个方法后，计算光标就方便了很多。
 */

public class Custom501EditTextView extends View {

    public Custom501EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        String text = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";

        paint.setTextSize(50);


        int length = text.length();
        float advance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            advance = paint.getRunAdvance(text, 0, length, 0, length, false, length - 6);
        }
        float offsetX = 0;
        float offsetY = 50;
        canvas.drawText(text + advance, offsetX, offsetY, paint);
        canvas.drawLine(offsetX + advance, offsetY - 50, offsetX + advance, offsetY + 10, paint);


    }
}
