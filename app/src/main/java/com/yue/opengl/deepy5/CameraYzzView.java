package com.yue.opengl.deepy5;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.Face;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.yue.opengl.deep5.camera.CameraApi14;
import com.yue.opengl.deep5.camera.CameraSize;
import com.yue.opengl.deep5.camera.ICamera;
import com.yue.opengl.deep5.preview.CameraDrawer;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.yue.opengl.deepy5.CameraId.REAR;

/**
 * Time:2021/6/18
 * Author:yzzCool
 * Description:
 */
public class CameraYzzView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private static final String TAG = "CameraView";
    private IYzzCamera mCameraApi;
    private CameraId mBackCameraId = REAR;

    private CameraYzzDrawer mCameraDrawer;
    private int width;
    private int height;
    private Face[] facefaces;

    public CameraYzzView(Context context) {
        super(context);
        initEGL();
        initCameraApi(context);
    }

    private void initEGL() {
        //open gl step 1
        setEGLContextClientVersion(2);
        setRenderer(this);
        //只有刷新之后，才会去重绘
        setRenderMode(RENDERMODE_WHEN_DIRTY);

    }

    private void initCameraApi(Context context) {

        mCameraApi = new Camera2Api(context);
        mCameraDrawer = new CameraYzzDrawer(context.getResources());
        mCameraApi.setFaceDetectCallback(new IYzzCamera.FaceDetectCallback() {
            @Override
            public void onFaceDetect(Face[] faces) {
                facefaces = faces;
            }
        });
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCameraDrawer.onSurfaceCreated(gl, config);

        mCameraApi.setPreviewTexture(mCameraDrawer.getSurfaceTexture());//先设置过来
        mCameraApi.open(mBackCameraId);
        mCameraDrawer.setCameraId(mBackCameraId);


        CameraSize previewSize = mCameraApi.getPreviewSize();
        int previewSizeWidth = previewSize.getWidth();
        int previewSizeHeight = previewSize.getHeight();

        mCameraDrawer.setPreviewSize(previewSizeWidth, previewSizeHeight);

        mCameraDrawer.setSensorRect(mCameraApi.getSensorRect());

        //默认使用的GLThread.每次刷新的时候，都强制要求是刷新这个GLSurfaceView
        mCameraDrawer.getSurfaceTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                requestRender();
            }
        });
        mCameraApi.preview();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mCameraDrawer.onSurfaceChanged(gl, width, height);
        //设置ViewPort是必须要做的
        GLES20.glViewport(0, 0, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mCameraDrawer.setFaces(facefaces);
        mCameraDrawer.onDrawFrame(gl);

    }

    @Override
    public void onPause() {
        super.onPause();
        mCameraApi.close();
    }

   public int takePhotoFromGL = 0;

    public void takePhoto(IYzzCamera.TakePhotoCallback callback) {
        if (takePhotoFromGL != 1) {
            if (mCameraApi != null) {
                float[] mtx = new float[16];
                mCameraDrawer.getSurfaceTexture().getTransformMatrix(mtx);
                mCameraApi.takePhoto(callback);
            }
        } else {
            //直接使用OpenGL的方式
            queueEvent(() -> {
                //发送到GLThread中进行
                //这里传递的长宽。其实就是openGL surface的长款，一定要注意了！！
                ByteBuffer rgbaBuf = ByteBuffer.allocateDirect(width * height * 4);
                rgbaBuf.position(0);
                long start = System.nanoTime();
                GLES20.glReadPixels(0, 0, width, height, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE,
                        rgbaBuf);
                long end = System.nanoTime();
                Log.d(TAG, "gl glReadPixels cost = " + (end - start));
                callback.onTakePhoto(rgbaBuf.array(), width, height);
            });
        }

    }
}
