package com.yue.camera.process1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.yue.camera.google.preview.Camera2BasicPreviewFragment;
import com.yue.yueapp.R;
import com.yue.yueapp.utils.LoggerUtils;

import java.util.Arrays;


/**
 * Time: 2021/6/1
 * Author:yzzCool
 * Description: camera preview，有预览
 */
public class CameraBase1Activity extends AppCompatActivity {
    private static final String FRAGMENT_DIALOG = "dialog";
    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private TextureView mTextureView;
    private String mCameraId;
    private CameraDevice mCameraDevice;

    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CaptureRequest mPreviewRequest;
    private CameraCaptureSession mCameraCaptureSession;
    private Surface surface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_camera_base1);
        mTextureView = findViewById(R.id.camera_base_text);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }


    /**
     * 1. openCamera  得到 CameraDevice
     */
    private void openCamera() {
        LoggerUtils.e("openCamera openCamera");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            new ConfirmationPermissionDialog().show(getSupportFragmentManager(), FRAGMENT_DIALOG);
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = manager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        try {
            //这个地方应该就是打开相机
            manager.openCamera(mCameraId, mStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            //开启预览
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {

        }
    };

    /**
     * 2. mCameraDevice.createCaptureSession
     *   得到 CameraCaptureSession
     */
    private void createCameraPreviewSession() {
        try {
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            surface = new Surface(texture);
            mCameraDevice.createCaptureSession(Arrays.asList(surface),
                    cameraCaptureSessionStateCallback, null
            );
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 3. mCameraCaptureSession.setRepeatingRequest
     *  开始预览
     */
    CameraCaptureSession.StateCallback cameraCaptureSessionStateCallback = new CameraCaptureSession.StateCallback() {

        @Override
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            mCameraCaptureSession = cameraCaptureSession;
            mPreviewRequestBuilder.addTarget(surface);
            try {
                mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                mPreviewRequest = mPreviewRequestBuilder.build();
                mCameraCaptureSession.setRepeatingRequest(mPreviewRequest, null, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

        }
    };

    /**
     * Shows OK/Cancel confirmation dialog about camera permission.
     */
    public static class ConfirmationPermissionDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Fragment parent = getParentFragment();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.request_permission)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            parent.requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA_PERMISSION);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Activity activity = parent.getActivity();
                                    if (activity != null) {
                                        activity.finish();
                                    }
                                }
                            })
                    .create();
        }
    }

}