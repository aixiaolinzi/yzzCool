package com.yue.opengl.deep2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.yue.opengl.utils.LoadGLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 *Time: 2021/4/19
 *Author:yzzCool
 *Description:在GLRenderer2基础上绘制不同的颜色。
 */
public class GLRendererColor2 implements GLSurfaceView.Renderer {
    public static final int BYTES_PER_FLOAT = 4;//每个浮点数:坐标个数* 4字节
    private final Context mContext;
    private FloatBuffer vertexBuffer;//顶点缓冲

    //顶点的坐标系
    private static float TRIANGLE_COORDINATES[] = {
            //Order of coordinates: X, Y, Z
            -0.5f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f   // bottom right
    };
    private int mProgramObjectId;

    //在数组中，一个顶点需要3个来描述其位置，需要3个偏移量
    private static final int COORDINATES_PER_VERTEX = 3;
    private static final int COORDINATES_PER_COLOR = 0;

    //在数组中，描述一个顶点，总共的顶点需要的偏移量。这里因为只有位置顶点，所以和上面的值一样
    private static final int TOTAL_COMPONENT_COUNT = COORDINATES_PER_VERTEX + COORDINATES_PER_COLOR;
    //一个点需要的byte偏移量。
    private static final int STRIDE = TOTAL_COMPONENT_COUNT * BYTES_PER_FLOAT;

    /********color 2.1  投影矩阵 添加相应的变量 start********************/
    // 颜色，rgba  更换颜色
    //float TRIANGLE_COLOR[] = {0.5176471f, 0.77254903f, 0.9411765f, 1.0f};

    private FloatBuffer mColorBuffer;//颜色缓冲
    static final int COLOR_PER_VERTEX = 4;//向量维度
    private final int vertexColorStride = COLOR_PER_VERTEX * 4; // 4*4=16
    float colors[] = new float[]{
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f//绿
    };
    /********color 2.1  投影矩阵 添加相应的变量 end********************/


    /********2.1  投影矩阵 添加相应的变量 start********************/
    private float[] mProjectionMatrix = new float[16];
    private int uMatrix;
    /********2.1  投影矩阵 添加相应的变量 end********************/

    public GLRendererColor2(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //初始化顶点字节缓冲区, 每个浮点数:坐标个数* 4字节
        ByteBuffer bb = ByteBuffer.allocateDirect(TRIANGLE_COORDINATES.length * 4);
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(TRIANGLE_COORDINATES);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标

        /********color 2.2  添加相应的缓冲buffer start********************/
        ByteBuffer colorBuffer = ByteBuffer.allocateDirect(colors.length * 4);
        colorBuffer.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        mColorBuffer = colorBuffer.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        mColorBuffer.put(colors);// 将坐标添加到FloatBuffer
        mColorBuffer.position(0);//设置缓冲区以读取第一个坐标
        /********color 2.2  添加相应的缓冲buffer end********************/

        //0.简单的给窗口填充一种颜色
        GLES20.glClearColor(1.0f, 0f, 0f, 0f);//rgba

        //在创建的时候，去创建这些着色器
        //1.根据String进行编译。得到着色器id
        int vertexShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_VERTEX_SHADER, "deepColor2.vert");
        int fragmentShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_FRAGMENT_SHADER, "deepColor2.frag");

        //2.继续处理。取得到program
        mProgramObjectId = GLES20.glCreateProgram();
        //将shaderId绑定到program当中
        GLES20.glAttachShader(mProgramObjectId, vertexShaderObjectId);
        GLES20.glAttachShader(mProgramObjectId, fragmentShaderObjectId);

        //3.最后，启动GL link program
        GLES20.glLinkProgram(mProgramObjectId);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //在窗口改变的时候调用
        GLES20.glViewport(0, 0, width, height);//GL视口


        /******************2.2  设置相应的比例 start**********************************/
        //主要还是长宽进行比例缩放
        float aspectRatio = width > height ?
                (float) width / (float) height :
                (float) height / (float) width;

        if (width > height) {
            //横屏。需要设置的就是左右。
            Matrix.orthoM(mProjectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1f, -1.f, 1f);
        } else {
            //竖屏。需要设置的就是上下
            Matrix.orthoM(mProjectionMatrix, 0, -1, 1f, -aspectRatio, aspectRatio, -1.f, 1f);
        }
        /******************2.2  设置相应的比例 end **********************************/

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //0.glClear（）的唯一参数表示需要被清除的缓冲区。当前可写的颜色缓冲
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //0.先使用这个program?这一步应该可以放到onCreate中进行
        GLES20.glUseProgram(mProgramObjectId);

        //1.根据我们定义的取出定义的位置
        int uPosition = GLES20.glGetAttribLocation(mProgramObjectId, "vPosition");
        //2.开始启用我们的position
        GLES20.glEnableVertexAttribArray(uPosition);



        uMatrix = GLES20.glGetUniformLocation(mProgramObjectId, "u_Matrix");


        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                uPosition,  //上面得到的id
                COORDINATES_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                vertexBuffer);


        /********2.3  mProjectionMatrix矩阵值做相应的传递 start********************/
        //传递给着色器
        GLES20.glUniformMatrix4fv(uMatrix,1,false,mProjectionMatrix,0);
        /********2.3  mProjectionMatrix矩阵值做相应的传递 end********************/

        /********color 2.3  开始绘制相应的颜色 start********************/


        //注意颜色句柄不是uniform了,获取片元着色器的vColor成员的句柄
        int uColor = GLES20.glGetAttribLocation(mProgramObjectId, "aColor");

        //启用三角形顶点颜色的句柄
        GLES20.glEnableVertexAttribArray(uColor);

        //准备三角顶点颜色数据
        GLES20.glVertexAttribPointer(
                uColor,
                COORDINATES_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);



//        //取出颜色
//        int uColor = GLES20.glGetUniformLocation(mProgramObjectId, "vColor");
//
//        //开始绘制
        //设置绘制三角形的颜色
//        GLES20.glUniform4fv(
//                uColor,
//                1,
//                TRIANGLE_COLOR,
//                0
//        );

        /********color 2.3  开始绘制相应的颜色 end********************/

        //绘制三角形.
        //draw arrays的几种方式
        //GL_TRIANGLES三角形
        //GL_TRIANGLE_STRIP三角形带的方式(开始的3个点描述一个三角形，后面每多一个点，多一个三角形)
        //GL_TRIANGLE_FAN扇形(可以描述圆形)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, TRIANGLE_COORDINATES.length / 3);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(uPosition);

    }

}
