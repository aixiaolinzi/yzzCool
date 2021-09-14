package example.yzz.openglwar.episode3.ui.warepisode11;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 */
public class WorldView3_11 extends GLSurfaceView {

    private WorldRenderer3_11 mRenderer;
    private WorldRenderer3_11_2 worldRenderer3_11_2;

    public WorldView3_11(Context context) {
        this(context, null);
    }

    public WorldView3_11(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
//        mRenderer = new WorldRenderer3_11(getContext());
        worldRenderer3_11_2 = new WorldRenderer3_11_2(getContext());
        setRenderer(worldRenderer3_11_2);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
