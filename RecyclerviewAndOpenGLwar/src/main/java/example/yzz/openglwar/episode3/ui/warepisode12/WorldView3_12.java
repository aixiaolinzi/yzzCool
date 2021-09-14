package example.yzz.openglwar.episode3.ui.warepisode12;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class WorldView3_12 extends GLSurfaceView {

    private WorldRenderer3_12 worldRenderer3_12;

    public WorldView3_12(Context context) {
        this(context, null);
    }

    public WorldView3_12(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        worldRenderer3_12 = new WorldRenderer3_12(getContext());
        setRenderer(worldRenderer3_12);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
