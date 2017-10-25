package com.yue.yueapp.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.yue.uilibrary.paowuxian.Custom601View;
import com.yue.uilibrary.paowuxian.Custom602View;
import com.yue.uilibrary.paowuxian.Custom603View;
import com.yue.yueapp.R;
import com.yue.yueapp.utils.Logger;

import java.util.ArrayList;

/**
 * Created by yzz on 2017/8/30.
 * <p>
 * 学习抛物线的进阶。
 * 这是进阶列表，第一节的进阶。
 */

public class Advanced1Fragment extends BaseFragment {

    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_advanced1, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_advanced);
        ArrayList<View> views = new ArrayList<>();
        View view3 = inflater.inflate(R.layout.advanced3_custom, null, false);
        views.add(view3);
        views.add(inflater.inflate(R.layout.advanced2_custom, null, false));
        views.add(inflater.inflate(R.layout.advanced1_custom, null, false));
        viewPager.setAdapter(new MyPagerAdapter(views));

        ImageView imageView = (ImageView) view3.findViewById(R.id.iv_custom);


        ViewPropertyAnimator animate = imageView.animate();
        animate.translationX(500);
        animate.rotationY(45);
        animate.setDuration(5000);


        Custom602View custom = (Custom602View) view3.findViewById(R.id.custom601);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(custom, "progress", 0, 330);
        translationX.setDuration(5000);
        translationX.start();



        Custom603View custom3 = (Custom603View) view3.findViewById(R.id.custom603);
        ObjectAnimator translationX3 = ObjectAnimator.ofFloat(custom3, "progress", 0, 360);
        translationX3.setDuration(10000);
        translationX3.start();





        /**
         * 通用功能
         * 1. setDuration(int duration) 设置动画时长
         * 2. setInterpolator(Interpolator interpolator) 设置 Interpolator
         * Interpolator 其实就是速度设置器。
         * 简单介绍一下每一个 Interpolator:::
         * 1.AccelerateDecelerateInterpolator先加速再减速。这是默认的 Interpolator.
         * 2.LinearInterpolator匀速。
         * 3.AccelerateInterpolator持续加速。（指数曲线）在整个动画过程中，一直在加速，直到动画结束的一瞬间，直接停止。
         * 4.DecelerateInterpolator持续减速直到 0。动画开始的时候是最高速度
         * 5.AnticipateInterpolator先回拉一下再进行正常动画轨迹。效果看起来有点像投掷物体或跳跃等动作前的蓄力。
         * 6.OvershootInterpolator动画会超过目标值一些，然后再弹回来。
         * 7.AnticipateOvershootInterpolator上面这两个的结合版：开始前回拉，最后超过一些然后回弹。
         * 8.BounceInterpolator在目标值处弹跳。有点像玻璃球掉在地板上的效果。
         * 9.CycleInterpolator这个也是一个正弦 / 余弦曲线，不过它和 AccelerateDecelerateInterpolator 的区别是，
         * 它可以自定义曲线的周期，所以动画可以不到终点就结束，也可以到达终点后回弹，
         * 回弹的次数由曲线的周期决定，曲线的周期由 CycleInterpolator() 构造方法的参数决定。
         * 10.PathInterpolator自定义动画完成度 / 时间完成度曲线。
         * 用这个 Interpolator 你可以定制出任何你想要的速度模型。
         * 定制的方式是使用一个 Path 对象来绘制出你要的动画完成度 / 时间完成度曲线。
         *
         *Path interpolatorPath = new Path();
         //情况一:
         // 匀速
         interpolatorPath.lineTo(1, 1);
         //情况二：
         // 先以「动画完成度 : 时间完成度 = 1 : 1」的速度匀速运行 25%
         interpolatorPath.lineTo(0.25f, 0.25f);
         // 然后瞬间跳跃到 150% 的动画完成度
         interpolatorPath.moveTo(0.25f, 1.5f);
         // 再匀速倒车，返回到目标点
         interpolatorPath.lineTo(1, 1);
         //注意：不能出现，在一个时间段上面没有动画，或者有两个动画。
         * 11.FastOutLinearInInterpolator加速运动。（贝塞尔曲线）
         * FastOutLinearInInterpolator 的曲线公式是用的贝塞尔曲线，而 AccelerateInterpolator 用的是指数曲线。
         * 12.FastOutSlowInInterpolator先加速再减速。（贝塞尔曲线）
         * 13.LinearOutSlowInInterpolator持续减速。（贝塞尔曲线）
         *
         */


        //3.1 ViewPropertyAnimator.setListener() / ObjectAnimator.addListener()
        //最常用监听器的使用，方法的开始，取消，结束等等。
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //当动画开始执行时，这个方法被调用。
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //当动画结束时，这个方法被调用。
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //当动画被通过 cancel() 方法取消时，这个方法被调用。
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //当动画通过 setRepeatMode() / setRepeatCount() 或 repeat() 方法重复执行时，这个方法被调用。
                //由于 ViewPropertyAnimator 不支持重复，所以这个方法对 ViewPropertyAnimator 相当于无效。
            }
        };
        animate.setListener(animatorListener);
        translationX.addListener(animatorListener);

        //3.2 ViewPropertyAnimator.setUpdateListener() / ObjectAnimator.addUpdateListener()
        //

        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //当动画的属性更新时（不严谨的说，即每过 10 毫秒，动画的完成度更新时），这个方法被调用。
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animate.setUpdateListener(animatorUpdateListener);
            translationX.addUpdateListener(animatorUpdateListener);
        }

        //3.3 ObjectAnimator.addPauseListener()
        //3.3 ViewPropertyAnimator.withStartAction/EndAction()





        return view;
    }


    class MyPagerAdapter extends PagerAdapter {

        private ArrayList<View> viewLists;

        public MyPagerAdapter(ArrayList<View> lists) {
            super();
            viewLists = lists;
        }

        @Override
        public int getCount() {
            Logger.e("长度++" + viewLists.size());
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }
}
