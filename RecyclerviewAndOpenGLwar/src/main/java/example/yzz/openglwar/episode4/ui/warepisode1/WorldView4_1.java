package example.yzz.openglwar.episode4.ui.warepisode1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class WorldView4_1 extends GLSurfaceView {

    private WorldRenderer4_1 worldRenderer41;

    public WorldView4_1(Context context) {
        this(context, null);
    }

    public WorldView4_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        worldRenderer41 = new WorldRenderer4_1(getContext());
        setRenderer(worldRenderer41);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
