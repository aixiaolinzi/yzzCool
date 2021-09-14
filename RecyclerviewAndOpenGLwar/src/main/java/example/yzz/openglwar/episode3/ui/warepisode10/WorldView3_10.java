package example.yzz.openglwar.episode3.ui.warepisode10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class WorldView3_10 extends GLSurfaceView {

    private WorldRenderer3_10 mRenderer;

    public WorldView3_10(Context context) {
        this(context, null);
    }

    public WorldView3_10(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        mRenderer = new WorldRenderer3_10(getContext());
        setRenderer(mRenderer);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
