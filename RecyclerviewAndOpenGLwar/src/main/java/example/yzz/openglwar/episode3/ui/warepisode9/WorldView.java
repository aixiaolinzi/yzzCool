package example.yzz.openglwar.episode3.ui.warepisode9;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class WorldView extends GLSurfaceView {

    private WorldRenderer mRenderer;

    public WorldView(Context context) {
        this(context, null);
    }

    public WorldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new WorldRenderer(getContext());
        setRenderer(mRenderer);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
