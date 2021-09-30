package example.yzz.openglwar.episode4.utils;

import example.yzz.openglwar.episode3.abs.Cons;


/**
 * Time: 2021/9/30
 * Author:yzzCool
 * Description: 在WarShape的基础上，绘制面。
 */
public class WarFaceShape implements Cloneable {
    private float[] mVertex;//顶点
    private float[] mColor;//颜色
    private int mDrawType;//绘制类型
    private short[] idx;//索引数组

    public WarFaceShape(float[] mVertex, short[] idx, float[] mColor, int mDrawType) {
        this.mVertex = mVertex;
        this.idx = idx;
        this.mColor = mColor;
        this.mDrawType = mDrawType;
    }

    public float[] getVertex() {
        return mVertex;
    }

    public void setVertex(float[] mVertex) {
        this.mVertex = mVertex;
    }

    public float[] getColor() {
        return mColor;
    }

    public void setColor(float[] mColor) {
        this.mColor = mColor;
    }

    public int getDrawType() {
        return mDrawType;
    }

    public void setDrawType(int mDrawType) {
        this.mDrawType = mDrawType;
    }

    public int getCount() {
        return mVertex.length / Cons.DIMENSION_3;
    }

    /**
     * 深拷贝
     *
     * @return 形状副本
     */
    public WarFaceShape clone() {
        WarFaceShape clone = null;
        try {
            clone = (WarFaceShape) super.clone();
            float[] vertex = new float[mVertex.length];
            float[] color = new float[mColor.length];
            System.arraycopy(mVertex, 0, vertex, 0, mVertex.length);
            System.arraycopy(mColor, 0, color, 0, mColor.length);
            clone.mVertex = vertex;
            clone.mColor = color;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 移动并创建新图形
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public WarFaceShape moveAndCreate(float x, float y, float z) {
        WarFaceShape clone = clone();
        clone.move(x, y, z);
        return clone;
    }

    /**
     * 仅移动图形
     *
     * @param x
     * @param y
     * @param z
     */
    public void move(float x, float y, float z) {
        for (int i = 0; i < mVertex.length; i++) {
            if (i % 3 == 0) {//x
                mVertex[i] += x;
            }
            if (i % 3 == 1) {//y
                mVertex[i] += y;
            }
            if (i % 3 == 2) {//y
                mVertex[i] += z;
            }
        }
    }


    public short[] getIdx() {
        return idx;
    }

    public void setIdx(short[] idx) {
        this.idx = idx;
    }
}
