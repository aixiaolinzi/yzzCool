package example.yzz.openglwar.episode3.ui.warepisode11;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 和 GLRenderer2_6_2 的代码是一样的。
 * 重点 setLookAtM 设置相机的位置，图像就变化了。
 */
public class GLRenderer2_7 implements GLSurfaceView.Renderer {
    private Context mContext;

    Triangle2_7 mTriangle262;
    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];


    public GLRenderer2_7(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        mTriangle262 = new Triangle2_7(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);//GL视口

        float ratio = (float) width / height;
        //透视投影矩阵--截锥
        Matrix.frustumM(mProjectionMatrix, 0,
                -ratio, ratio, -1, 1,
                3, 7);
        // 设置相机位置(视图矩阵)
        Matrix.setLookAtM(mViewMatrix, 0,
                0, 0, -3,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // 设置相机位置(视图矩阵) ，一开始眼睛在(0, 0, -3),看下图你就知道为什么画的时候面有问题了
        Matrix.setLookAtM(mViewMatrix, 0,
                0, 0, -3,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);


        // 设置相机位置(视图矩阵)，现在调到后上方(2,2,-5)
        Matrix.setLookAtM(mViewMatrix, 0,
                2f, 2f, -5.0f,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);


        // 下面的隐藏，只是使用上面的。下面的是和GLRenderer1_3一样的。
        // 计算投影和视图转换
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mViewMatrix, 0);

        mTriangle262.draw(mMVPMatrix);
    }

}
