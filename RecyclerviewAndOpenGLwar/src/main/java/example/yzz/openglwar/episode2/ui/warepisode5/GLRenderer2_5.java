package example.yzz.openglwar.episode2.ui.warepisode5;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description: 和 GLRenderer1_3的代码是一样的。
 */
public class GLRenderer2_5 implements GLSurfaceView.Renderer {
    private Context mContext;

    Triangle2_5 mTriangle124;
    Triangle2_5_3 mTriangle253;
    Triangle2_5_4 mTriangle254;
    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];

    public GLRenderer2_5(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        //mTriangle124 = new Triangle2_5(mContext);
        //mTriangle253 = new Triangle2_5_3(mContext);
        mTriangle254 = new Triangle2_5_4(mContext);
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


        // 下面的是和GLRenderer1_3一样的。
        // 计算投影和视图转换
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mViewMatrix, 0);

        //mTriangle124.draw(mMVPMatrix);
        //mTriangle253.draw(mMVPMatrix);
        mTriangle254.draw(mMVPMatrix);
    }


}
