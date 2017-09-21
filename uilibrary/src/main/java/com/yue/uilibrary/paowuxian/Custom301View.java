package com.yue.uilibrary.paowuxian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/**
 * Created by yzz on 2017/9/12.
 * HenCoder Android 开发进阶：自定义 View 1-3 文字的绘制
 */

public class Custom301View extends View {


    Paint paintNew = new Paint();

    public Custom301View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1 Canvas 绘制文字的方式
         * Canvas 的文字绘制方法有三个：drawText() drawTextRun() 和 drawTextOnPath()。
         */

        //1.1 drawText(String text, float x, float y, Paint paint)
        //drawText() 是 Canvas 最基本的绘制文字的方法：给出文字的内容和位置， Canvas 按要求去绘制文字。
        //注意：这个坐标并不是文字的左上角，而是一个与左下角比较接近的位置。
//        String text = "你的名字";
//        canvas.drawText(text, 200, 200, paint);

        //1.2 drawTextRun()
        //处理阿拉伯语言使用到。

        //1.3 drawTextOnPath()
        //沿着一条 Path 来绘制文字。
        //canvas.drawPath(path, paint); // 把 Path 也绘制出来，理解起来更方便
        //canvas.drawTextOnPath("Hello HeCoder", path, 0, 0, paint);
        //注意：所以记住一条原则： drawTextOnPath() 使用的 Path ，拐弯处全用圆角，别用尖角。
        // hOffset 和 vOffset。它们是文字相对于 Path 的水平偏移量和竖直偏移量，利用它们可以调整文字的位置。


        //1.4 StaticLayout
        //可以是文字换行的处理。

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(100);
        String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        StaticLayout staticLayout = new StaticLayout(text1, textPaint, 600, Layout.Alignment.ALIGN_NORMAL, 100, 100, true);

        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
        StaticLayout staticLayout2 = new StaticLayout(text2, textPaint, 600, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        //分析：：
        // StaticLayout 的构造方法是
        // StaticLayout(CharSequence source, TextPaint paint, int width,
        //              Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad)，
        // 其中参数里：
        // width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
        // align 是文字的对齐方向；
        // spacingmult 是行间距的倍数，通常情况下填 1 就好；
        // spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
        // includeadd 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。

        //如果你需要进行多行文字的绘制，并且对文字的排列和样式没有太复杂的花式要求，那么使用 StaticLayout 就好。
//        canvas.save();
//        canvas.translate(50, 100);
//        staticLayout.draw(canvas);
//        canvas.translate(0, 200);
//        staticLayout2.draw(canvas);
//        canvas.restore();

        /**
         * 2. Paint 对文字绘制的辅助
         * Paint 对文字绘制的辅助，有两类方法：设置显示效果的和测量文字尺寸的。
         * 2.1 设置显示效果类
         */


        //2.1.1 setTextSize(float textSize)
        //设置文字大小。


        //2.1.2 setTypeface(Typeface typeface)
        //设置字体
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);


        //2.1.3 setFakeBoldText(boolean fakeBoldText)
        //是否使用伪粗体。之所以叫做伪粗体，并不是选用更高的weight的字体让字体变粗，
        //而是通过程序在运行时把文字描粗了。


        //2.1.4 setStrikeThruText(boolean strikeThruText)
        //是否加删除线。
        textPaint.setStrikeThruText(true);


        //2.1.5 setUnderlineText(boolean underlineText)
        //是否加下划线。
        textPaint.setUnderlineText(true);

        //2.1.6 setTextSkewX(float skewX)
        //设置文字横向错切角度。其实就是文字倾斜度的啦。
        textPaint.setTextSkewX(-0.5f);

        //2.1.7 setTextScaleX(float scaleX)
        //设置文字横向放缩。也就是文字变胖变瘦。


        //2.1.8 setLetterSpacing(float letterSpacing)
        //设置字符间距。默认值是 0。


        //2.1.9 setFontFeatureSettings(String settings)
        //用 CSS 的 font-feature-settings 的方式来设置文字。
        //paint.setFontFeatureSettings("smcp"); // 设置 "small caps"
        //canvas.drawText("Hello HenCoder", 100, 150, paint);


        //2.1.10 setTextAlign(Paint.Align align)
        //设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT。


        //2.1.11 setTextLocale(Locale locale) / setTextLocales(LocaleList locales)
        //设置绘制所使用的 Locale。


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            textPaint.setTextLocale(Locale.ENGLISH);

        //2.1.12 setHinting(int mode)
        //设置是否启用字体的 hinting （字体微调）。
        //让矢量字体在尺寸过小的时候得到针对性的修正，从而提高显示效果。
        //手机屏幕的像素密度已经非常高，几乎不会再出现字体尺寸小到需要靠 hinting 来修正的情况，所以这个方法其实……没啥用了。


        //2.1.13 setElegantTextHeight(boolean elegant)
        //声明：这个方法对中国人没用，不想看的话可以直接跳过，无毒副作用。
        //设置是否开启文字的 elegant height 。开启之后，文字的高度就变优雅了（误）
        //阿拉伯语言会用到的。国语英语没用。


        //2.1.14 setSubpixelText(boolean subpixelText)
        //是否开启次像素级的抗锯齿（ sub-pixel anti-aliasing ）。
        //也是没有什么用的。


        //2.1.15 setLinearText(boolean linearText)
        //这个方法老实说我从没用过，也始终没有搞懂它是什么意思，就不强行装逼了。
//        canvas.drawText("我的心里", 200, 200, textPaint);


        /**
         * 2.2 测量文字尺寸类
         *
         */

        //2.2.1 float getFontSpacing()
        // 获取推荐的行距
        // 它的作用是当你要手动绘制多行文字（而不是使用 StaticLayout）的时候，
        // 可以在换行的时候给 y 坐标加上这个值来下移文字。
        // 有利于画下一行的文字。

        //2.2.2 FontMetircs getFontMetrics()
        //获取 Paint 的 FontMetrics。
        //FontMetrics 是个相对专业的工具类，
        //它提供了几个文字排印方面的数值：ascent, descent, top, bottom, leading。


        /**
         *
         baseline: 上图中黑色的线。前面已经讲过了，它的作用是作为文字显示的基准线。


         ascent / descent: 上图中绿色和橙色的线，它们的作用是限制普通字符的顶部和底部范围。
         普通的字符，上不会高过 ascent ，下不会低过 descent ，
         例如上图中大部分的字形都显示在 ascent 和 descent 两条线的范围内。
         具体到 Android 的绘制中，
         ascent 的值是图中绿线和 baseline 的相对位移，它的值为负（因为它在 baseline 的上方）；
         descent 的值是图中橙线和 baseline 相对位移，值为正（因为它在 baseline 的下方）。


         top / bottom: 上图中蓝色和红色的线，它们的作用是限制所有字形（ glyph ）的顶部和底部范围。
         除了普通字符，有些字形的显示范围是会超过 ascent 和 descent 的，
         而 top 和 bottom 则限制的是所有字形的显示范围，包括这些特殊字形。
         例如上图的第二行文字里，就有两个泰文的字形分别超过了 ascent 和 descent 的限制，
         但它们都在 top 和 bottom 两条线的范围内。
         具体到 Android 的绘制中，
         top 的值是图中蓝线和 baseline的相对位移，它的值为负（因为它在 baseline 的上方）；
         bottom 的值是图中红线和 baseline 相对位移，值为正（因为它在 baseline 的下方）。


         leading: 这个词在上图中没有标记出来，因为它并不是指的某条线和 baseline 的相对位移。
         leading 指的是行的额外间距，即对于上下相邻的两行，上行的 bottom 线和下行的 top 线的距离，
         也就是上图中第一行的红线和第二行的蓝线的距离（对，就是那个小细缝）。



         FontMetrics 提供的就是 Paint 根据当前字体和字号，得出的这些值的推荐值。
         它把这些值以变量的形式存储，供开发者需要时使用。
         FontMetrics.ascent：float 类型。
         FontMetrics.descent：float 类型。
         FontMetrics.top：float 类型。
         FontMetrics.bottom：float 类型。
         FontMetrics.leading：float 类型。
         另外，ascent 和 descent 这两个值还可以通过 Paint.ascent() 和 Paint.descent() 来快捷获取。
         */


        Paint.FontMetrics fontMetrics = paintNew.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float bottom = fontMetrics.bottom;
        float descent = fontMetrics.descent;
        float leading = fontMetrics.leading;
        float top = fontMetrics.top;


        //2.2.3 getTextBounds(String text, int start, int end, Rect bounds)
        //获取文字的显示范围
        //参数里，text 是要测量的文字，start 和 end 分别是文字的起始和结束位置，
        //bounds 是存储文字显示范围的对象，方法在测算完成之后会把结果写进 bounds。
        //想要在哪一个详细的坐标，只需要修改bounds的值就好。


        //2.2.4 float measureText(String text)
        //测量文字的宽度并返回。
        float measureText = paintNew.measureText(text1);

        //getTextBounds: 它测量的是文字的显示范围（关键词：显示）。
        //measureText(): 它测量的是文字绘制时所占用的宽度（关键词：占用）。
        //往往需要占用比他的实际显示宽度更多一点的宽度，这样显示好看。


        //2.2.5 getTextWidths(String text, float[] widths)
        //获取字符串中每个字符的宽度，并把结果填入参数widths


        //2.2.6 int breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth)
        //这个方法也是用来测量文字宽度的。（测量的是文字的个数）
        //（如果文字的宽度超出了上限，那么在临近超限的位置截断文字。）
        //但和 measureText() 的区别是， breakText() 是在给出宽度上限的前提下测量文字的宽度。


        //2.2.7 光标相关
        //对于 EditText 以及类似的场景，会需要绘制光标。
        //其实就是自定义一个EditText，不要与现成的EditText混淆。
        //2.2.7.1 getRunAdvance(CharSequence text, int start, int end, int contextStart,
        //                         int contextEnd, boolean isRtl, int offset)


        paintNew.setTextSize(50);
        String textHello = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";
        int line=textHello.length();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paintNew.getRunAdvance(textHello,0,line,0,line,false,line);
        }
        canvas.drawText(textHello, 0, 200, paintNew);


        //2.2.7.2 getOffsetForAdvance(CharSequence text, int start, int end, int contextStart,
        //                                      int contextEnd, boolean isRtl, float advance)
        //给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）。
        //方法的参数很简单： text 是要测量的文字；
        // start end 是文字的起始和结束坐标；contextStart contextEnd 是上下文的起始和结束坐标；
        // isRtl 是文字方向；advance是给出的位置的像素值。
        // 填入参数，对应的字符偏移量将作为返回值返回。



        //2.2.8 hasGlyph(String string)
        //检查指定的字符串中是否是一个单独的字形 (glyph）。
        // 最简单的情况是，string 只有一个字母（比如 a）。ab就不是，两个字符不算是一个字形。




    }
}
