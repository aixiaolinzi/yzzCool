package com.yue.opengl.deep1;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer1 implements GLSurfaceView.Renderer {
    public static final int BYTES_PER_FLOAT = 4;//每个浮点数:坐标个数* 4字节
    private FloatBuffer vertexBuffer;//顶点缓冲
    private final String vertexShaderCode =//顶点着色代码
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";
    private final String fragmentShaderCode =//片元着色代码
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    //顶点的坐标系
    private static float TRIANGLE_COORDS[] = {
            //Order of coordinates: X, Y, Z
            0.5f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f   // bottom right
    };
    private int mProgramObjectId;


    //在数组中，一个顶点需要3个来描述其位置，需要3个偏移量
    private static final int COORDS_PER_VERTEX = 3;
    private static final int COORDS_PER_COLOR = 0;

    //在数组中，描述一个顶点，总共的顶点需要的偏移量。这里因为只有位置顶点，所以和上面的值一样
    private static final int TOTAL_COMPONENT_COUNT = COORDS_PER_VERTEX + COORDS_PER_COLOR;
    //一个点需要的byte偏移量。
    private static final int STRIDE = TOTAL_COMPONENT_COUNT * BYTES_PER_FLOAT;

    // 颜色，rgba  更换颜色
    float TRIANGLE_COLOR[] = {0.5176471f, 0.77254903f, 0.9411765f, 1.0f};

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //初始化顶点字节缓冲区
        //每个浮点数:坐标个数* 4字节
        ByteBuffer bb = ByteBuffer.allocateDirect(TRIANGLE_COORDS.length * 4);
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(TRIANGLE_COORDS);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标


        //0.简单的给窗口填充一种颜色
        GLES20.glClearColor(1.0f, 0f, 0f, 0f);//rgba

        //在创建的时候，去创建这些着色器
        //1.根据String进行编译。得到着色器id
        int vertexShaderObjectId = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShaderObjectId = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

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
        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                uPosition,  //上面得到的id
                COORDS_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                vertexBuffer);

        //取出颜色
        int uColor = GLES20.glGetUniformLocation(mProgramObjectId, "vColor");

        //开始绘制
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(
                uColor,
                1,
                TRIANGLE_COLOR,
                0
        );

        //绘制三角形.
        //draw arrays的几种方式
        //GL_TRIANGLES三角形
        //GL_TRIANGLE_STRIP三角形带的方式(开始的3个点描述一个三角形，后面每多一个点，多一个三角形)
        //GL_TRIANGLE_FAN扇形(可以描述圆形)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, TRIANGLE_COORDS.length / 3);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(uPosition);


    }


    /**
     * 加载作色器
     *
     * @param type       着色器类型
     *                   顶点着色 {@link GLES20.GL_VERTEX_SHADER}
     *                   片元着色 {@link GLES20.GL_FRAGMENT_SHADER}
     * @param shaderCode 着色代码
     * @return 作色器
     */
    private static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);//创建着色器
        if (shader == 0) {//加载失败直接返回
            return 0;
        }
        GLES20.glShaderSource(shader, shaderCode);//加载着色器源代码
        GLES20.glCompileShader(shader);//编译
        return shader;
    }


}
