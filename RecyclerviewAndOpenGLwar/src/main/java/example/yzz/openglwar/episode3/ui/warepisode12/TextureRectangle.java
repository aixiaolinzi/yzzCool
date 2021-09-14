package example.yzz.openglwar.episode3.ui.warepisode12;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import example.yzz.openglwar.utils.WarGLUtils;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class TextureRectangle {
    private static final String TAG = "Triangle";
    private Context mContext;
    private int mProgram;//OpenGL ES 程序
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    private FloatBuffer vertexBuffer;//顶点缓冲
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12
    static final int COORDS_PER_VERTEX = 3;//数组中每个顶点的坐标数
    static final int c = 1;//数组中每个顶点的坐标数
    static float sCoo[] = {   //以逆时针顺序
            -c, c, 0.0f, // p0
            -c, -c, 0.0f, // p1
            c, -c, 0.0f, // p2
    };

    private final float[] textureCoo = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
    };
    //索引数组
    private short[] idx = {
            1, 2, 3,
    };

//    static float sCoo[] = {   //以逆时针顺序
//            -c, c, 0.0f, // p0
//            -c, -c, 0.0f, // p1
//            c, -c, 0.0f, // p2
//            c, c, 0.0f, //p3
//    };
//
//    private final float[] textureCoo = {
//            0.0f,0.0f,
//            0.0f,1.0f,
//            1.0f,0.0f,
//            1.0f,1.0f,
//    };
//
//    //索引数组
//    private short[] idx = {
//            1, 2, 3,
//            0, 1, 3,
//    };

    static final int TEXTURE_PER_VERTEX = 2;//数组中每个顶点的坐标数
    private final int vertexTextureStride = TEXTURE_PER_VERTEX * 4; // 4*4=16

    private ShortBuffer idxBuffer;
    private FloatBuffer mTextureCooBuffer;

    public TextureRectangle(Context context) {
        mContext = context;
        //初始化顶点字节缓冲区
        bufferData();//缓冲顶点数据
        initProgram();//初始化OpenGL ES 程序
    }

    /**
     * 缓冲数据
     */
    private void bufferData() {
        vertexBuffer = WarGLUtils.getFloatBuffer(sCoo);
        mTextureCooBuffer = WarGLUtils.getFloatBuffer(textureCoo);
        idxBuffer = WarGLUtils.getShortBuffer(idx);
    }

    /**
     * 初始化OpenGL ES 程序
     */
    private void initProgram() {
        ////顶点着色
        int vertexShader = WarGLUtils.loadShaderAssets(mContext,
                GLES20.GL_VERTEX_SHADER, "war2_8.vert");
        //片元着色
        int fragmentShader = WarGLUtils.loadShaderAssets(mContext,
                GLES20.GL_FRAGMENT_SHADER, "war2_8.frag");

        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目

        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "vCoordinate");
        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    public void draw(float[] mvpMatrix, int texId) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //启用三角形顶点颜色的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);

        //顶点矩阵变换
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);

        //准备三角顶点坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle,//int indx, 索引
                COORDS_PER_VERTEX,//int size,大小
                GLES20.GL_FLOAT,//int type,类型
                false,//boolean normalized,//是否标准化
                vertexStride,// int stride,//跨度
                vertexBuffer);// java.nio.Buffer ptr//缓冲

        //准备三角顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                TEXTURE_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexTextureStride,
                mTextureCooBuffer);
        //绑定纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texId);

        //绘制
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, idx.length, GLES20.GL_UNSIGNED_SHORT, idxBuffer);
        //禁用顶点数组:
        //禁用index指定的通用顶点属性数组。
        // 默认情况下，禁用所有客户端功能，包括所有通用顶点属性数组。
        // 如果启用，将访问通用顶点属性数组中的值，
        // 并在调用顶点数组命令（如glDrawArrays或glDrawElements）时用于呈现
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
