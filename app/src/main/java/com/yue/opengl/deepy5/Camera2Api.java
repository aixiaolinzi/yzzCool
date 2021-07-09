package com.yue.opengl.deepy5;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.yue.opengl.deep5.camera.AspectRatio;
import com.yue.opengl.deep5.camera.CameraSize;
import com.yue.yueapp.utils.LoggerUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * for api 14 Android 4.0
 * <p>
 * Camera主要涉及参数
 * 1. 预览画面的大小
 * 2. pic图片的大小
 * 3. 对焦模式
 * 4. 闪光灯模式
 * <p>
 * 需要需要注意相机旋转方向的问题
 * 1. 通过设置相机的displayOritation 和 pramaras中的旋转方向。主要它可以保存在选摄像头的时候的参数中
 * 2. 因为预览图是 通过SurfaceTextureView 中显示。可以设置matrix来控制它的旋转。  如这里是手动去控制纹理的绘制的话，则可以自己控制viewMatrix来控制
 */
public class Camera2Api implements IYzzCamera {
    private static final String TAG = "CameraApi14";
    private Context mContext;

    /*
    当前的相机Id
     */
    private CameraId mCameraId;
    private CameraDevice mCameraDevice;
    SurfaceTexture glSurfaceTexture = null;

    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CaptureRequest mPreviewRequest;
    private CameraCaptureSession mCaptureSession;


    //想要的尺寸。
    private int mDesiredHeight = 1920;
    private int mDesiredWidth = 1080;
    public CameraSize mPreviewSize;
    public CameraSize mPicSize;
    /*
     * 当前相机的高宽比
     */
    private AspectRatio mDesiredAspectRatio;
    private AtomicBoolean isPictureCaptureInProgress = new AtomicBoolean(false);
    private TakePhotoCallback photoCallBack;


    private Rect sensorRect;
    private FaceDetectCallback mFaceDetectCallback;


    public Camera2Api(Context context) {
        mContext = context;
        mDesiredHeight = 1920;
        mDesiredWidth = 1080;
        mPreviewSize = new CameraSize(mDesiredWidth, mDesiredHeight);
        mPicSize = new CameraSize(mDesiredWidth, mDesiredHeight);

        //创建默认的比例.因为后置摄像头的比例，默认的情况下，都是旋转了270
        mDesiredAspectRatio = AspectRatio.of(mDesiredWidth, mDesiredHeight).inverse();
    }

    @Override
    public boolean open(CameraId cameraId) {
        mCameraId = cameraId;
        CameraManager manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);


        try {
            //这个地方应该就是打开相机
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return false;
            }

            String[] cameraIdList = manager.getCameraIdList();
            CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics(cameraIdList[0]);
            sensorRect = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            LoggerUtils.e("OPEN sensorRect " + sensorRect + "sensorRect.top " + sensorRect.top + " sensorRect.bottom " + sensorRect.bottom
                    + " sensorRect.left " + sensorRect.left + " sensorRect.right " + sensorRect.right);
            manager.openCamera(cameraIdList[0], mStateCallback, new Handler(Looper.getMainLooper()));
            LoggerUtils.e("OPEN" + Thread.currentThread().getName());
        } catch (CameraAccessException e) {
            e.printStackTrace();
            LoggerUtils.e("OPEN  catch" + e.getMessage());
        }

        return false;
    }


    CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            LoggerUtils.e("OPEN  mStateCallback  camera");
            //开启预览
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {

            LoggerUtils.e("OPEN  mStateCallback  onDisconnected  ");

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {

            LoggerUtils.e("OPEN  mStateCallback  onError  " + error);

        }

        @Override
        public void onClosed(@NonNull CameraDevice camera) {
            super.onClosed(camera);
            LoggerUtils.e("OPEN  mStateCallback  onClosed  ");
        }

    };


    /**
     * Creates a new {@link CameraCaptureSession} for camera preview.
     */
    private void createCameraPreviewSession() {
        try {
            glSurfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());

            assert glSurfaceTexture != null;
            Surface surface = new Surface(glSurfaceTexture);
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mPreviewRequestBuilder.addTarget(surface);
            mPreviewRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL);

            mCameraDevice.createCaptureSession(Arrays.asList(surface),
                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                            mCaptureSession = cameraCaptureSession;
                            try {
                                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO);
                                mPreviewRequest = mPreviewRequestBuilder.build();

                                mCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback, new Handler(Looper.getMainLooper()));
                                LoggerUtils.e("createCameraPreviewSession  setRepeatingRequest  ");
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

                        }
                    }, new Handler(Looper.getMainLooper())
            );
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private CameraCaptureSession.CaptureCallback mCaptureCallback =
            new CameraCaptureSession.CaptureCallback() {
                private void process(CaptureResult result) {
                    Face[] faces = result.get(CaptureResult.STATISTICS_FACES);
                    mFaceDetectCallback.onFaceDetect(faces);

                    try {
                        if (faces != null && faces.length > 0) {
                            Face face = faces[0];

                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                    int left;
                    int top;
                    int right;
                    int bottom;
                }

                @Override
                public void onCaptureProgressed(@NonNull CameraCaptureSession session,
                                                @NonNull CaptureRequest request,
                                                @NonNull CaptureResult partialResult) {
                }

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                               @NonNull CaptureRequest request,
                                               @NonNull TotalCaptureResult result) {
                    process(result); //每一帧都有回调。
                }
            };


    @Override
    public void setAspectRatio(AspectRatio aspectRatio) {
        this.mDesiredAspectRatio = aspectRatio;
    }

    @Override
    public boolean preview() {

        return false;
    }

    @Override
    public boolean close() {

        return false;
    }

    @Override
    public void setPreviewTexture(SurfaceTexture surfaceTexture) {

        glSurfaceTexture = surfaceTexture;

    }

    @Override
    public CameraSize getPreviewSize() {
        return mPreviewSize;
    }

    @Override
    public CameraSize getPictureSize() {
        return mPicSize;
    }

    @Override
    public Rect getSensorRect() {
        return sensorRect;
    }

    @Override
    public void takePhoto(TakePhotoCallback callback) {
        this.photoCallBack = callback;
    }

    @Override
    public void setFaceDetectCallback(FaceDetectCallback callback) {
        this.mFaceDetectCallback = callback;
    }
}
