package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.yue.uilibrary.R;

/**
 * Created by yzz on 2017/9/26.
 * 处理动起来的动画。
 */

public class Custom602View extends View {

    float progress = 0;

    public Custom602View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

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
        //818*818
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - width / 2;
        int y = centerY - height / 2;


        canvas.save();
        Camera myCamera = new Camera();


        canvas.translate(centerX, centerY); // 旋转之后把投影移动回来
        canvas.rotate((progress > 30 ? progress < 300 ? progress - 30 : 270 : 0));
        myCamera.save();
        myCamera.rotateX(progress < 30 ? progress : 30); // 旋转 Camera 的三维空间

        myCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas

        canvas.clipRect(-width, 0, width, height);
        canvas.rotate(-(progress > 30 ? progress < 300 ? progress - 30 : 270 : 0));
        canvas.translate(-centerX, -centerY);
        myCamera.restore();

        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


        canvas.save();
        canvas.translate(centerX, centerY); // 旋转之后把投影移动回来
        canvas.rotate((progress > 30 ? progress < 300 ? progress - 30 : 270 : 0));
        myCamera.save();
        myCamera.rotateX(-(progress > 300 ? progress-300 : 0)); // 旋转 Camera 的三维空间
        myCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas

        canvas.clipRect(-width, -height, width, 0);
        canvas.rotate(-(progress > 30 ? progress < 300 ? progress - 30 : 270 : 0));
        canvas.translate(-centerX, -centerY);
        myCamera.restore();

        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


        if (progress < 300) {
            //就是旋转270度
            float angle = progress - 30;
        }


        Log.e("打印", "得到的progress的值" + progress);

    }
}
