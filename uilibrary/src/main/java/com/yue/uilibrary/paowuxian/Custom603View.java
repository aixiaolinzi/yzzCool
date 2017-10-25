package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yue.uilibrary.R;

/**
 * Created by yzz on 2017/9/26.
 * 测试一下旋转切图
 */

public class Custom603View extends View {
    public Custom603View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float progress = 0;

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public float getProgress() {
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bing_one);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int x = centerX - width / 2;
        int y = centerY - height / 2;



        Camera myCamera = new Camera();

        canvas.save();

        canvas.translate(centerX, centerY);
        canvas.rotate(progress);

        myCamera.save();
        myCamera.rotateX(30);
        myCamera.applyToCanvas(canvas);


        canvas.rotate(-progress);
        canvas.translate(-centerX, -centerY);
        myCamera.restore();

        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


    }
}
