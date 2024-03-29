package example.yzz.openglwar.episode2.ui.warepisode7;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import example.yzz.openglwar.utils.WarGLUtils;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 在 Triangle2_6_2 基础上，修改
 */
public class Triangle2_7 {
    private Context mContext;

    private FloatBuffer vertexBuffer;//顶点缓冲


    private final int mProgram;
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//矩阵句柄

    private final int vertexCount = sCoo.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12

    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;

    //成员变量
    private FloatBuffer mColorBuffer;//颜色缓冲
    static final int COLOR_PER_VERTEX = 4;//向量维度
    private final int vertexColorStride = COLOR_PER_VERTEX * 4; // 4*4=16

    /**
     *  1. 绘制第一面
     */
//    //点位数组
//    static float sCoo[] = {
//            -0.5f, 0.5f, 0.5f,//p0
//            -0.5f, -0.5f, 0.5f,//p1
//            -0.5f, -0.5f, -0.5f,//p2
//            -0.5f, 0.5f, -0.5f,//p3
//    };
//
//    //索引数组
//    private short[] idx = {
//            0, 1, 3,
//            1, 2, 3,
//    };
//    float colors[] = new float[]{
//            1f, 1f, 0.0f, 1.0f,//黄
//            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
//            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
//            1.0f, 0.0f, 1.0f, 1.0f,//紫色
//    };

    /**
     *  2. 追加第二面
     */
//    static float sCoo[] = {
//            -0.5f, 0.5f, 0.5f,//p0
//            -0.5f, -0.5f, 0.5f,//p1
//            -0.5f, -0.5f, -0.5f,//p2
//            -0.5f, 0.5f, -0.5f,//p3
//
//            -0.5f, 0.5f, 0.5f, //p4
//            -0.5f, -0.5f, 0.5f,//p5
//            0.5f, -0.5f, 0.5f, //p6
//            0.5f, 0.5f, 0.5f   //p7
//    };
//
//    //索引数组
//    private short[] idx = {
//            0, 1, 3,
//            1, 2, 3,
//
//            0+4, 1+4, 3+4,
//            1+4, 2+4, 3+4,
//    };
//
//    float colors[] = new float[]{
//            1f, 1f, 0.0f, 1.0f,//黄
//            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
//            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
//            1.0f, 0.0f, 1.0f, 1.0f,//紫色
//            1f, 1f, 0.0f, 1.0f,//黄
//            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
//            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
//            1.0f, 0.0f, 1.0f, 1.0f,//紫色
//    };

    /**
     *  3. 其他所有面
     */
    static float sCoo[] = {
            //A面
            -0.5f, 0.5f, 0.5f,//p0
            -0.5f, -0.5f, 0.5f,//p1
            -0.5f, -0.5f, -0.5f,//p2
            -0.5f, 0.5f, -0.5f,//p3
            //B面
            -0.5f, 0.5f, 0.5f,//p4
            -0.5f, -0.5f, 0.5f,//p5
            0.5f, -0.5f, 0.5f,//p6
            0.5f, 0.5f, 0.5f,//p7
            //C面
            0.5f, 0.5f, 0.5f,//p8
            0.5f, -0.5f, 0.5f,//p9
            0.5f, -0.5f, -0.5f,//p10
            0.5f, 0.5f, -0.5f,//p11
            //D面
            -0.5f, 0.5f, 0.5f,//p12
            0.5f, 0.5f, 0.5f,//p13
            0.5f, 0.5f, -0.5f,//p14
            -0.5f, 0.5f, -0.5f,//p15
            //E面
            -0.5f, -0.5f, 0.5f,//p16
            0.5f, -0.5f, 0.5f,//p17
            0.5f, -0.5f, -0.5f,//p18
            -0.5f, -0.5f, -0.5f,//p19
            //F面
            -0.5f, 0.5f, -0.5f,//p20
            -0.5f, -0.5f, -0.5f,//p21
            0.5f, -0.5f, -0.5f,//p22
            0.5f, 0.5f, -0.5f,//p23
    };

    //索引数组
    private short[] idx = {
            0, 1, 3,//A
            1, 2, 3,
            0 + 4, 1 + 4, 3 + 4,//B
            1 + 4, 2 + 4, 3 + 4,
            0 + 4 * 2, 1 + 4 * 2, 3 + 4 * 2,//C
            1 + 4 * 2, 2 + 4 * 2, 3 + 4 * 2,
            0 + 4 * 3, 1 + 4 * 3, 3 + 4 * 3,//D
            1 + 4 * 3, 2 + 4 * 3, 3 + 4 * 3,
            0 + 4 * 4, 1 + 4 * 4, 3 + 4 * 4,//E
            1 + 4 * 4, 2 + 4 * 4, 3 + 4 * 4,
            0 + 4 * 5, 1 + 4 * 5, 3 + 4 * 5,//F
            1 + 4 * 5, 2 + 4 * 5, 3 + 4 * 5,
    };

    float colors[] = new float[]{
            //A
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //B
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //C
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //D
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //E
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //F
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
    };

    public Triangle2_7(Context context) {
        this.mContext =  context;
        //初始化顶点字节缓冲区
        ByteBuffer bb = ByteBuffer.allocateDirect(sCoo.length * 4);//每个浮点数:坐标个数* 4字节
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(sCoo);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);



        int vertexShader = WarGLUtils.loadShaderAssets(this.mContext,
                GLES20.GL_VERTEX_SHADER, "war2_6_2.vert");
        //片元着色
        int fragmentShader = WarGLUtils.loadShaderAssets(this.mContext,
                GLES20.GL_FRAGMENT_SHADER, "war2_6_2.frag");

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

        //注意颜色句柄不是uniform了,获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");

        //启用三角形顶点颜色的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);

        //准备三角顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                COLOR_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);


        //索引缓冲
        ShortBuffer idxBuffer = WarGLUtils.getShortBuffer(idx);

        //绘制
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, idx.length,
                GLES20.GL_UNSIGNED_SHORT, idxBuffer);

        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
