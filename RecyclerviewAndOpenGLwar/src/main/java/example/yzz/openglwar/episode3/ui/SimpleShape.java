package example.yzz.openglwar.episode3.ui;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import example.yzz.openglwar.episode3.abs.Cons;
import example.yzz.openglwar.episode3.abs.RenderAble;
import example.yzz.openglwar.episode3.abs.WarShape;
import example.yzz.openglwar.utils.WarGLUtils;


/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:  在 WorldShape3_10 的基础上进行的处理。
 *
 * 虽然只能画些简单的东西，但画画辅助线还是蛮方便的，一个SimpleShape类
 * 构造方法有变化。mVertex和 mVertex都是传入的，所以肯定会有不同。
 */
public class SimpleShape extends RenderAble {
    private int mProgram;//OpenGL ES 程序
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    private FloatBuffer mColorBuffer;//颜色缓冲
    private final int vertexColorStride = Cons.DIMENSION_4 * 4; // 4*4=16
    private FloatBuffer mVertexBuffer;//顶点缓冲
    private final int vertexStride = Cons.DIMENSION_3 * 4; // 3*4=12

    //略...
    private WarShape mShape;

    public SimpleShape(Context context, WarShape shape) {
        super(context);
        mShape = shape;
        mColorBuffer = WarGLUtils.getFloatBuffer(mShape.getColor());
        mVertexBuffer = WarGLUtils.getFloatBuffer(mShape.getVertex());
        initProgram();
    }



    private void initProgram() {
        //顶点着色
        int vertexShader = WarGLUtils.loadShaderAssets(mContext,
                GLES20.GL_VERTEX_SHADER, "war3_9_world.vert");
        //片元着色
        int fragmentShader = WarGLUtils.loadShaderAssets(mContext,
                GLES20.GL_FRAGMENT_SHADER, "war3_9_world.frag");
        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目
        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    @Override
    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        //启用顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //启用顶点颜色的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);
        //顶点矩阵变换
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //准备顶点坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle,//int indx, 索引
                Cons.DIMENSION_3,//int size,大小
                GLES20.GL_FLOAT,//int type,类型
                false,//boolean normalized,//是否标准化
                vertexStride,// int stride,//跨度
                mVertexBuffer);// java.nio.Buffer ptr//缓冲
        //准备顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                Cons.DIMENSION_4,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);


        /**
         * 绘制连线
         */
        GLES20.glLineWidth(10);//设置线的宽度
        int count = mShape.getVertex().length / Cons.DIMENSION_3;
        //GLES20.glDrawArrays(GLES20.GL_POINTS, 0, count);
        //GLES20.glDrawArrays(GLES20.GL_LINES, 0, count);
        //GLES20.glDrawArrays(GLES20.GL_LINE_STRIP, 0, count);
        GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 0, count);

    }
}

