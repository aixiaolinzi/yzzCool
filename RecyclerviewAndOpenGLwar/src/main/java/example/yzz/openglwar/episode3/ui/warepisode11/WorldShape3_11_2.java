package example.yzz.openglwar.episode3.ui.warepisode11;

import android.content.Context;
import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.List;

import example.yzz.openglwar.episode3.abs.Cons;
import example.yzz.openglwar.episode3.abs.OP;
import example.yzz.openglwar.episode3.abs.RenderAble;
import example.yzz.openglwar.episode3.abs.WarShape;
import example.yzz.openglwar.episode3.ui.SimpleShape;


/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 在 WorldShape3_11  基础上，   添加 OP<RenderAble>。、
 * 加入 SimpleShape 完成相应的封装。
 *
 * 第二关卡：简单封装
 *
 * 如果图形创建在WorldRenderer中，感觉很不舒服，毕竟会有很多形状，
 *
 * WorldRenderer的本意只是为了渲染以及视角的控制，并不希望图形掺杂其中
 * WorldShape可以专门绘制形状，由它统一向WorldRenderer输出形状
 * 既然WorldShape总管图形，那么操作图形，在所难免,建一个OP接口，目前只放两个方法
 *
 *
 */
public class WorldShape3_11_2 extends RenderAble implements OP<RenderAble> {


    List<RenderAble> mRendererAbles;
    private float[] mVertex = new float[]{
            -0.5f, 0.0f, -0.5f,//A
            -0.5f, 0.0f, 0.5f,//B
            0.5f, 0.0f, 0.5f,//C
            0.5f, 0.0f, -0.5f,//D
    };
    private float[] mColor = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            0.21960784f, 0.56078434f, 0.92156863f, 1.0f,
    };


    public WorldShape3_11_2(Context context) {
        super(context);
        mRendererAbles = new ArrayList<>();

        WarShape coo = new WarShape(Cons.VERTEX_COO, Cons.COLOR_COO, GLES20.GL_LINES);
        WarShape ground = new WarShape(mVertex, mColor, GLES20.GL_LINE_LOOP);
        add(
                new SimpleShape(mContext, coo),
                new SimpleShape(mContext, ground));

    }

    @Override
    public void draw(float[] mvpMatrix) {
        for (RenderAble rendererAble : mRendererAbles) {
            rendererAble.draw(mvpMatrix);
        }
    }

    @Override
    public void add(RenderAble... rendererAbles) {
        for (RenderAble rendererAble : rendererAbles) {
            mRendererAbles.add(rendererAble);
        }
    }

    @Override
    public void remove(int id) {
        if (id >= mRendererAbles.size()) {
            return;
        }
        mRendererAbles.remove(id);
    }

}

