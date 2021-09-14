package example.yzz.openglwar.episode3.ui.warepisode10;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import example.yzz.openglwar.episode3.abs.Cons;
import example.yzz.openglwar.episode3.abs.RenderAble;
import example.yzz.openglwar.utils.WarGLUtils;


/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 在Triangle2_5_3基础上，绘制六边形。
 */
public class WorldShape3_10 extends RenderAble {
    private int mProgram;//OpenGL ES 程序
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    private FloatBuffer mColorBuffer;//颜色缓冲
    private final int vertexColorStride = Cons.DIMENSION_4 * 4; // 4*4=16
    private FloatBuffer mVertexBuffer;//顶点缓冲
    private final int vertexStride = Cons.DIMENSION_3 * 4; // 3*4=12
    /**
     * 1.  点形状绘制
     */
//    private float[] mVertex = new float[]{
//            0.0f, 0.0f, 0.0f
//    };
//
//    private float[] mColor = new float[]{
//            1.0f, 1.0f, 1.0f, 1.0f,
//    };

    /**
     * 2. 第三关卡：绘制四点
     */
    private float[] mVertex = new float[]{
            -0.5f, 0.0f, -0.5f,
            -0.5f, 0.0f, 0.5f,
            0.5f, 0.0f, 0.5f,
            0.5f, 0.0f, -0.5f,
    };

    private float[] mColor = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
    };


    public WorldShape3_10(Context context) {
        super(context);
        mColorBuffer = WarGLUtils.getFloatBuffer(mColor);
        mVertexBuffer = WarGLUtils.getFloatBuffer(mVertex);
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
        int count = mVertex.length / Cons.DIMENSION_3;
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, count);
    }
}

