package example.yzz.openglwar.episode2.ui.warepisode6;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLView2_6 extends GLSurfaceView {

    private GLRenderer2_6 mRenderer;
    private Context mContext;

    public GLView2_6(Context context) {
        this(context,null);
    }

    public GLView2_6(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new GLRenderer2_6(mContext);
        setRenderer(mRenderer);//设置渲染器
    }


}
