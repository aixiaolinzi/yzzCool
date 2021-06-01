package com.yue.opengl.deep3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import com.yue.opengl.utils.LoadGLUtils;
import com.yue.yueapp.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



/**
 *Time: 2021/4/21
 *Author:yzzCool
 *Description: 纹理的绘制
 * 注释：texture 3 是这次的修改
 *
 */
public class GLRendererTexture3 implements GLSurfaceView.Renderer {
    public static final int BYTES_PER_FLOAT = 4;//每个浮点数:坐标个数* 4字节
    private final Context mContext;
    private FloatBuffer vertexBuffer;//顶点缓冲

    //顶点的坐标系
    private static float TRIANGLE_COORDINATES[] = {
            //Order of coordinates: X, Y, Z
            -1.0f, 1.0f, 0.0f, 0.0f,
            -1.0f, -1.0f, 0.0f, 1.0f, //bottom left
            1.0f, 1.0f, 1.0f, 0.0f, // top right
            1.0f, -1.0f, 1.0f, 1.0f, // bottom right
    };
    private int mProgramObjectId;

    //在数组中，一个顶点需要3个来描述其位置，需要3个偏移量
    private static final int COORDS_PER_VERTEX = 2;
    private static final int COORDS_PER_ST = 2;
    private static final int TOTAL_COMPONENT_COUNT = COORDS_PER_VERTEX + COORDS_PER_ST;
    private static final int STRIDE = TOTAL_COMPONENT_COUNT * BYTES_PER_FLOAT;


    /********2.1  投影矩阵 添加相应的变量 start********************/
    private float[] mProjectionMatrix = new float[16];
    private int uMatrix;
    private int mTextureId;
    private int mSubTextureId;

    /********2.1  投影矩阵 添加相应的变量 end********************/



    public GLRendererTexture3(Context mContext) {
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


        //0.简单的给窗口填充一种颜色
        GLES20.glClearColor(1.0f, 0f, 0f, 0f);//rgba

        //在创建的时候，去创建这些着色器
        //1.根据String进行编译。得到着色器id
        int vertexShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_VERTEX_SHADER, "deepTexture3.vert");
        int fragmentShaderObjectId = LoadGLUtils.loadShaderAssets(mContext, GLES20.GL_FRAGMENT_SHADER, "deepTexture3.frag");

        //2.继续处理。取得到program
        mProgramObjectId = GLES20.glCreateProgram();
        //将shaderId绑定到program当中
        GLES20.glAttachShader(mProgramObjectId, vertexShaderObjectId);
        GLES20.glAttachShader(mProgramObjectId, fragmentShaderObjectId);

        //3.最后，启动GL link program
        GLES20.glLinkProgram(mProgramObjectId);



        //生成纹理ID
        mTextureId = createTexture();
        mSubTextureId = createSubTexture();
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
        int uPosition = GLES20.glGetAttribLocation(mProgramObjectId, "a_Position");
        //2.开始启用我们的position
        GLES20.glEnableVertexAttribArray(uPosition);

        uMatrix = GLES20.glGetUniformLocation(mProgramObjectId, "u_Matrix");


        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                uPosition,  //上面得到的id
                COORDS_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                vertexBuffer);



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

        int uTexture = GLES20.glGetUniformLocation(mProgramObjectId, "u_TextureUnit1");
        int uTexture2 = GLES20.glGetUniformLocation(mProgramObjectId, "u_TextureUnit2");






        /********2.3  mProjectionMatrix矩阵值做相应的传递 start********************/
        //传递给着色器
        GLES20.glUniformMatrix4fv(uMatrix,1,false,mProjectionMatrix,0);
        /********2.3  mProjectionMatrix矩阵值做相应的传递 end********************/




//        //绑定和激活纹理
//        //因为我们生成了MIP，放到了GL_TEXTURE0 中，所以重新激活纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
//        //重新去半丁纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);

        //设置纹理的坐标
        GLES20.glUniform1i(uTexture, 0);


        //        //绑定和激活纹理
//        //因为我们生成了MIP，放到了GL_TEXTURE1 中，所以重新激活纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
//        //重新去半丁纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mSubTextureId);
        //设置纹理的坐标
        GLES20.glUniform1i(uTexture2, 1);


        //绘制三角形.
        //draw arrays的几种方式 GL_TRIANGLES三角形 GL_TRIANGLE_STRIP三角形带的方式(开始的3个点描述一个三角形，后面每多一个点，多一个三角形) GL_TRIANGLE_FAN扇形(可以描述圆形)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, TRIANGLE_COORDINATES.length / 3);


        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(uPosition);


    }



    //使用mip贴图来生成纹理，相当于将图片复制到openGL里面？
    private int createTexture() {
        final Bitmap mBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        //加载Bitmap
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher, options);
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

    //使用mip贴图来生成纹理，相当于将图片复制到openGL里面？
    private int createSubTexture() {
        final Bitmap mBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        //加载Bitmap
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.error_image, options);
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
