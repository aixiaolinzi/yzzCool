package example.yzz.openglwar.episode3.abs;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 * 3_10 开始追加。
 * 1. Shape ==> WarShape 修改一下名字。
 *
 * 2. 3_11
 * 第二关卡：简单封装
 * Shape的强化，移动与移动创建
 * 关于深拷贝和浅拷贝我就不废话了，移动创建中需要深拷贝(成员变量有引用数据类型)
 * Shape implements Cloneable
 * 追加moveAndCreate和clone 方法
 */
public class WarShape implements Cloneable {
    private float[] mVertex;//顶点
    private float[] mColor;//颜色
    private int mDrawType;//绘制类型

    public WarShape(float[] mVertex, float[] mColor, int mDrawType) {
        this.mVertex = mVertex;
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


    /**
     * 深拷贝
     * @return 形状副本
     */
    public WarShape clone() {
        WarShape clone = null;
        try {
            clone = (WarShape) super.clone();
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
     * @param x
     * @param y
     * @param z
     * @return
     */
    public WarShape moveAndCreate(float x, float y, float z) {
        WarShape clone = clone();
        clone.move(x, y, z);
        return clone;
    }

    /**
     * 仅移动图形
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


}
