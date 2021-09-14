package example.yzz.openglwar.episode1.ui.warepisode4;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import example.yzz.openglwar.utils.WarGLUtils;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class Triangle1_4 {
    private Context mContext;

    private FloatBuffer vertexBuffer;//顶点缓冲


    private final int mProgram;
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//颜色句柄
    private final int vertexCount = sCoo.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12

    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;
    static float sCoo[] = {   //以逆时针顺序
            0.0f, 0.0f, 0.0f, // 顶部
            -1.0f, -1.0f, 0.0f, // 左下
            1.0f, -1.0f, 0.0f  // 右下
    };

    // 颜色，rgba
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Triangle1_4(Context context) {
        this.mContext =  context;
        //初始化顶点字节缓冲区
        ByteBuffer bb = ByteBuffer.allocateDirect(sCoo.length * 4);//每个浮点数:坐标个数* 4字节
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(sCoo);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标

        int vertexShader = WarGLUtils.loadShaderAssets(this.mContext,
                GLES20.GL_VERTEX_SHADER, "war1_3.vert");
        //片元着色
        int fragmentShader = WarGLUtils.loadShaderAssets(this.mContext,
                GLES20.GL_FRAGMENT_SHADER, "war1_3.frag");

        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目
    }

    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);


        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);


        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);



        // 获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //为三角形设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
