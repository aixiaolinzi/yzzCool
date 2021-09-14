package example.yzz.openglwar.episode3.ui.warepisode10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLView2_6 extends GLSurfaceView {

    //private GLRenderer2_6 mRenderer;
    private GLRenderer2_6_2 mRenderer262;
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
        mRenderer262 = new GLRenderer2_6_2(mContext);
        setRenderer(mRenderer262);//设置渲染器
    }


}
