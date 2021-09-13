package example.yzz.openglwar.episode1.ui.warepisode4;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class GLView1_4 extends GLSurfaceView {

    private GLRenderer1_4 mRenderer;
    private Context mContext;

    public GLView1_4(Context context) {
        this(context,null);
    }

    public GLView1_4(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new GLRenderer1_4(mContext);
        setRenderer(mRenderer);//设置渲染器
    }


}
