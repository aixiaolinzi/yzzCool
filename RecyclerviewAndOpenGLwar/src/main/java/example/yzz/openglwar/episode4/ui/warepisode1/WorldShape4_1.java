package example.yzz.openglwar.episode4.ui.warepisode1;

import android.content.Context;
import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.List;

import example.yzz.openglwar.episode3.abs.Cons;
import example.yzz.openglwar.episode3.abs.OP;
import example.yzz.openglwar.episode3.abs.RenderAble;
import example.yzz.openglwar.episode3.abs.WarShape;
import example.yzz.openglwar.episode3.ui.SimpleShape;
import example.yzz.openglwar.episode4.utils.SimpleFaceShape;
import example.yzz.openglwar.episode4.utils.WarFaceShape;


/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 * 1. 在 WorldShape3_11_2  基础上，   追加不同的对象。
 * 绘制不同的图案
 *
 * 2.  追加4根竖直的线
 */
public class WorldShape4_1 extends RenderAble implements OP<RenderAble> {


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

    /**
     * 2.  追加4根竖直的线
     */
    private float[] mVertex2 = new float[]{
            0.5f, 0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,

            -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,

            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,

            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
    };
    private float[] mColor2 = new float[]{
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
    };


    //顶点坐标
    private float mVertexFace[] = {   //以逆时针顺序
            -0.5f, 0.5f, 0.5f, // p0
            -0.5f, -0.5f, 0.5f, // p1
            0.5f, -0.5f, 0.5f, // p2
            0.5f, 0.5f, 0.5f, //p3
    };

    //索引数组
    private short[] idxFace = {
            0, 1, 3,
            1, 2, 3
    };

    //顶点颜色
    float colorsFace[] = new float[]{
            1f, 1f, 0.0f, 1f,//黄
            0.05882353f, 0.09411765f, 0.9372549f,1f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1f,//绿
            1.0f, 0.0f, 1.0f,1f,//紫色
    };



    public WorldShape4_1(Context context) {
        super(context);
        mRendererAbles = new ArrayList<>();

        WarShape coo = new WarShape(Cons.VERTEX_COO, Cons.COLOR_COO, GLES20.GL_LINES);
        WarShape line = new WarShape(mVertex2, mColor2, GLES20.GL_LINES);

        WarShape ground = new WarShape(mVertex, mColor, GLES20.GL_LINE_LOOP);
        WarShape top = ground.moveAndCreate(0, 0.5f, 0);
        WarShape bottom = ground.moveAndCreate(0, -0.5f, 0);

        WarFaceShape face = new WarFaceShape(mVertexFace, idxFace, colorsFace, GLES20.GL_LINES);

        add(
                new SimpleShape(mContext, coo),
                new SimpleShape(mContext, line),

                new SimpleShape(mContext, top),
                new SimpleShape(mContext, bottom),
                new SimpleFaceShape(mContext, face)
        );

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

