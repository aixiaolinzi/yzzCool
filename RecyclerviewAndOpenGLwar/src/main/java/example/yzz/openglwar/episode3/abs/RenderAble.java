package example.yzz.openglwar.episode3.abs;

import android.content.Context;


/**
 *Time: 2021/9/14
 *Author:yzzCool
 *Description: Android多媒体之GLES2战记第三集--圣火之光
 * 开始就有这个抽象类，由抽象类来控制了。
 */
public abstract class RenderAble {
    protected Context mContext;

    public RenderAble(Context context) {
        mContext = context;
    }

    public abstract void draw(float[] mvpMatrix);

    /**
     * 求sin值
     *
     * @param θ 角度值
     * @return sinθ
     */
    public float sin(float θ) {
        return (float) Math.sin(Math.toRadians(θ));
    }

    /**
     * 求cos值
     *
     * @param θ 角度值
     * @return cosθ
     */
    public float cos(float θ) {
        return (float) Math.cos(Math.toRadians(θ));
    }
}
