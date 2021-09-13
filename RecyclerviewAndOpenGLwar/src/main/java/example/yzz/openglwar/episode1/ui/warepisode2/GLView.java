package example.yzz.openglwar.episode1.ui.warepisode2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLView extends GLSurfaceView {

    private GLRenderer mRenderer;

    public GLView(Context context) {
        this(context,null);
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);//设置渲染器
    }


}
