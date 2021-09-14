package example.yzz.openglwar.episode3.abs;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 * 3_10 开始追加。
 * Shape ==> WarShape 修改一下名字。
 *
 */
public class WarShape {
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
}
