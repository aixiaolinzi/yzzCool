package example.yzz.openglwar.episode3.ui.warepisode11;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import example.yzz.openglwar.episode3.abs.Cons;
import example.yzz.openglwar.episode3.abs.RenderAble;
import example.yzz.openglwar.episode3.abs.WarShape;
import example.yzz.openglwar.episode3.ui.SimpleShape;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 在 WorldRenderer3_11 的基础上，移除一下代码。
 * 移除 mCoo ，WorldShape3_11_2 这个里面有相应的封装。
 *
 * 第二关卡：简单封装
 *
 * 如果图形创建在WorldRenderer中，感觉很不舒服，毕竟会有很多形状，
 *
 * WorldRenderer的本意只是为了渲染以及视角的控制，并不希望图形掺杂其中
 * WorldShape可以专门绘制形状，由它统一向WorldRenderer输出形状
 * 既然WorldShape总管图形，那么操作图形，在所难免,建一个OP接口，目前只放两个方法
 *
 */
public class WorldRenderer3_11_2 implements GLSurfaceView.Renderer {
    private static final String TAG = "GLRenderer";
    //Model View Projection Matrix--模型视图投影矩阵
    private static float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private static final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private static final float[] mViewMatrix = new float[16];
    //变换矩阵
    private float[] mOpMatrix = new float[16];
    private Context mContext;
    private RenderAble mWorldShape;

    public WorldRenderer3_11_2(Context context) {
        mContext = context;
    }

    private int currDeg = 0;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//rgba
        mWorldShape = new WorldShape3_11_2(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);//GL视口
        float ratio = (float) width / height;
        //透视投影矩阵--截锥
        Matrix.frustumM(mProjectionMatrix, 0,
                -ratio, ratio, -1, 1,
                3, 9);
        // 设置相机位置(视图矩阵)
        Matrix.setLookAtM(mViewMatrix, 0,
                2f, 2f, -6.0f,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);
    }

    /**
     * 此方法会不断执行 {@link GLSurfaceView.RENDERMODE_CONTINUOUSLY}
     * 此方法执行一次 {@link GLSurfaceView.RENDERMODE_WHEN_DIRTY}
     *
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //初始化变换矩阵
        Matrix.setRotateM(mOpMatrix, 0, currDeg + 130, 0, 1, 0);
        Matrix.multiplyMM(mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
        mWorldShape.draw(mMVPMatrix);
        //打开深度检测
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        currDeg++;
    }

}
