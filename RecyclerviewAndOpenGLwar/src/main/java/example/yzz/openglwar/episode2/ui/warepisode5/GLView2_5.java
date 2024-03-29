package example.yzz.openglwar.episode2.ui.warepisode5;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLView2_5 extends GLSurfaceView {

    private GLRenderer2_5 mRenderer;
    private Context mContext;

    public GLView2_5(Context context) {
        this(context,null);
    }

    public GLView2_5(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new GLRenderer2_5(mContext);
        setRenderer(mRenderer);//设置渲染器
    }


}
