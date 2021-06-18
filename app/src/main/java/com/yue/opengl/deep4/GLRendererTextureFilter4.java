package com.yue.opengl.deep4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.yue.opengl.utils.LoadGLUtils;
import com.yue.yueapp.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

 /**
  *Time: 2021/6/18
  *Author:yzzCool
  *Description:纹理的绘制 带有滤镜的纹理
  */
public class GLRendererTextureFilter4 implements GLSurfaceView.Renderer {
    public static final int BYTES_PER_FLOAT = 4;//每个浮点数:坐标个数* 4字节
    private final Context mContext;
    private FloatBuffer vertexBuffer;//顶点缓冲

    //顶点的坐标系
    private static float TEXTURE_COORDS[] = {
            //Order of coordinates: X, Y,S,T
            -1.0f, 1.0f, 0.0f, 0.0f,
            -1.0f, -1.0f, 0.0f, 1.0f, //bottom left
            1.0f, 1.0f, 1.0f, 0.0f, // top right
            1.0f, -1.0f, 1.0f, 1.0f, // bottom right
    };
    private int mProgramObjectId;


    private static final int COORDS_PER_VERTEX = 2;
    private static final int COORDS_PER_ST = 2;
    private static final int TOTAL_COMPONENT_COUNT = COORDS_PER_VERTEX + COORDS_PER_ST;
    private static final int STRIDE = TOTAL_COMPONENT_COUNT * BYTES_PER_FLOAT;


    /********2.1  投影矩阵 添加相应的变量 start********************/

    private int mTextureId;

    /********2.1  投影矩阵 添加相应的变量 end********************/


    public GLRendererTextureFilter4(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //初始化顶点字节缓冲区, 每个浮点数:坐标个数* 4字节
        ByteBuffer bb = ByteBuffer.allocateDirect(TEXTURE_COORDS.length * 4);
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(TEXTURE_COORDS);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标


        //0.简单的给窗口填充一种颜色
        GLES20.glClearColor(1.0f, 0f, 0f, 0f);//rgba

        //在创建的时候，去创建这些着色器
        //1.根据String进行编译。得到着色器id
        int vertexShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_VERTEX_SHADER, "deepTexture3.vert");
        int fragmentShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_FRAGMENT_SHADER, "deepTexture4.frag");

        //2.继续处理。取得到program
        mProgramObjectId = GLES20.glCreateProgram();
        //将shaderId绑定到program当中
        GLES20.glAttachShader(mProgramObjectId, vertexShaderObjectId);
        GLES20.glAttachShader(mProgramObjectId, fragmentShaderObjectId);

        //3.最后，启动GL link program
        GLES20.glLinkProgram(mProgramObjectId);


        //0.先使用这个program?这一步应该可以放到onCreate中进行
        GLES20.glUseProgram(mProgramObjectId);

        //1.根据我们定义的取出定义的位置
        int aPosition = GLES20.glGetAttribLocation(mProgramObjectId, "a_Position");
        //2.开始启用我们的position
        GLES20.glEnableVertexAttribArray(aPosition);
        GLES20.glVertexAttribPointer(
                aPosition,  //上面得到的id
                COORDS_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                vertexBuffer);

        //生成纹理ID
        mTextureId = createTexture();
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






        //取到这个属性值和应用。偏移到我们的ST坐标使用。
        int aCoordinate = GLES20.glGetAttribLocation(mProgramObjectId, "a_TextureCoordinates");
        vertexBuffer.position(COORDS_PER_VERTEX);
        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                aCoordinate,  //上面得到的id
                COORDS_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                vertexBuffer);

        GLES20.glEnableVertexAttribArray(aCoordinate);

        int uTexture = GLES20.glGetUniformLocation(mProgramObjectId, "u_TextureUnit");


        //绑定和激活纹理
        //因为我们生成了MIP，放到了GL_TEXTURE0 中，所以重新激活纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        //重新去绑定纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);
        //设置纹理的坐标
        GLES20.glUniform1i(uTexture, 0);


        //0 创建数组
        //黑白图片的公式：RGB 按照 0.2989 R，0.5870 G 和 0.1140 B 的比例构成像素灰度值。
        float[] grayFilterColorData = {0.299f, 0.587f, 0.114f};

        //1 .得到属性的location
        int uChangeColor = GLES20.glGetUniformLocation(mProgramObjectId, "u_ChangeColor");

        //2. 将数组传入
        GLES20.glUniform3fv(uChangeColor, 1, grayFilterColorData, 0);

        //绘制三角形.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, TEXTURE_COORDS.length / TOTAL_COMPONENT_COUNT);



    }


    //使用mip贴图来生成纹理，相当于将图片复制到openGL里面？
    private int createTexture() {
        final Bitmap mBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        //加载Bitmap
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.carp, options);
        //保存到textureObjectId
        int[] textureObjectId = new int[1];
        if (mBitmap != null && !mBitmap.isRecycled()) {
            //生成一个纹理，保存到这个数组中
            GLES20.glGenTextures(1, textureObjectId, 0);

            //绑定GL_TEXTURE_2D
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjectId[0]);


            //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
            //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

            //根据以上指定的参数，生成一个2D纹理
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmap, 0);


            //回收释放
            mBitmap.recycle();
            //因为我们已经复制成功了。所以就进行解除绑定。防止修改
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);


            return textureObjectId[0];
        }
        return 0;
    }


}
