package example.yzz.openglwar.episode1.ui.warepisode2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLRenderer1_2 implements GLSurfaceView.Renderer {
    private Context mContext;

    Triangle1_2 mTriangle12;
    Triangle1_2_4 mTriangle124;

    public GLRenderer1_2(Context context) {
        this.mContext = context;
    }

    /**
     * 加载作色器
     * @param type  顶点着色 {@link GLES20.GL_VERTEX_SHADER}
     *              片元着色 {@link GLES20.GL_FRAGMENT_SHADER}
     * @param shaderCode 着色代码
     * @return 作色器
     */
    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);//创建着色器
        GLES20.glShaderSource(shader, shaderCode);//添加着色器源代码
        GLES20.glCompileShader(shader);//编译
        return shader;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        //mTriangle12 = new Triangle1_2(mContext);
        mTriangle124 = new Triangle1_2_4(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);//GL视口
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //mTriangle12.draw();
        mTriangle124.draw();
    }


}
