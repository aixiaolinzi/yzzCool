package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by yzz on 2017/9/20.
 * <p>
 * HenCoder Android 开发进阶：自定义 View 1-5 绘制顺序
 * <p>
 * 下面开始使用道，不仅仅是术了。
 * <p>
 * 主讲绘制顺序
 */

public class Custom501View extends android.support.v7.widget.AppCompatEditText {
    public Custom501View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }


    /**
     * 具体来讲，一个完整的绘制过程会依次绘制以下几个内容：
     * 1.背景： drawBackground()不能重写，你如果要设置背景，只能用自带的 API 去设置（xml 布局文件的 android:background 属性以及 Java 代码的 View.setBackgroundXxx() 方法）
     * 2.主体(onDraw())
     * 3.子View(dispathDraw())
     * 4.滑动边缘渐变和滑动条：onDrawForeground()
     * 5.前景:onDrawForeground()
     * <p>
     * 注意：需要注意，前景的支持是在 Android 6.0（也就是 API 23）才加入的；
     * 之前其实也有，不过只支持 FrameLayout，而直到 6.0 才把这个支持放进了 View 类里。
     * <p>
     * 滑动边缘渐变和滑动条可以通过 xml 的 android:scrollbarXXX 系列属性
     * 或 Java 代码的 View.setXXXScrollbarXXX() 系列方法来设置；
     * 前景可以通过 xml 的 android:foreground属性或 Java 代码的 View.setForeground() 方法来设置。
     * 而重写 onDrawForeground() 方法，并在它的 super.onDrawForeground() 方法的上面或下面插入绘制代码，
     * 则可以控制绘制内容和滑动边缘渐变、滑动条以及前景的遮盖关系。
     * <p>
     * <p>
     * <p>
     * onDrawForeground()的使用：
     * 1. 写在 super.onDrawForeground() 的下面
     * 如果你把绘制代码写在了 super.onDrawForeground() 的下面，绘制代码会在滑动边缘渐变、滑动条和前景之后被执行，
     * 那么绘制内容将会盖住滑动边缘渐变、滑动条和前景。
     * 这样在下面绘制的东西不会受到影响。
     * 2. 写在 super.onDrawForeground() 的上面
     * 如果你把绘制代码写在了 super.onDrawForeground() 的上面，绘制内容就会在 dispatchDraw() 和 super.onDrawForeground() 之间执行，
     * 那么绘制内容会盖住子 View，但被滑动边缘渐变、滑动条以及前景盖住。
     * 写在上面，所有的属性都会显示在，相应的地方。
     * <p>
     * draw() 总调度方法的使用：：
     * draw() 是绘制过程的总调度方法。一个 View 的整个绘制过程都发生在 draw() 方法里。
     * 前面讲到的背景、主体、子 View 、滑动相关以及前景的绘制，它们其实都是在 draw() 方法里的。
     * <p>
     * 下面是详细的draw的方法流程。一定要记住。。。
     * public void draw(Canvas canvas) {
     * ...
     * drawBackground(Canvas); // 绘制背景（不能重写）
     * onDraw(Canvas); // 绘制主体
     * dispatchDraw(Canvas); // 绘制子 View
     * onDrawForeground(Canvas); // 绘制滑动相关和前景
     * ...
     * }
     * 1. 写在 super.draw() 的下面
     * 由于 draw() 是总调度方法，所以如果把绘制代码写在 super.draw() 的下面，
     * 那么这段代码会在其他所有绘制完成之后再执行，也就是说，它的绘制内容会盖住其他的所有绘制内容。
     * 它的效果和重写 onDrawForeground()，
     * 并把绘制代码写在 super.onDrawForeground() 时的效果是一样的：都会盖住其他的所有内容。
     * 2. 写在 super.draw() 的上面
     * 同理，由于 draw() 是总调度方法，所以如果把绘制代码写在 super.draw() 的上面，
     * 那么这段代码会在其他所有绘制之前被执行，所以这部分绘制内容会被其他所有的内容盖住，包括背景。
     * 是的，背景也会盖住它。
     */


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
