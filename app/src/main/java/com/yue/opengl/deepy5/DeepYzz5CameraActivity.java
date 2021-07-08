package com.yue.opengl.deepy5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.yue.camera.process1.CameraBase1Activity;
import com.yue.opengl.deep5.preview.CameraView;
import com.yue.yueapp.R;

import java.nio.ByteBuffer;


/**
 *Time: 2021/6/18
 *Author:yzzCool
 *Description: Android OpenGL ES(五)-结合相机进行预览/录制及添加滤镜
 *  https://www.jianshu.com/p/b36b6e17e818
 *
 * Camera ==>SurfaceTexture==>texture(samplerExternalOES) ==>draw to GLSurfaceView
 * 根据上面的博客更新，使用Camera 2来处理。
 *
 */
public class DeepYzz5CameraActivity extends AppCompatActivity {

    private static final String FRAGMENT_DIALOG = "dialog";
    private static final int REQUEST_CAMERA_PERMISSION = 2;
    private FrameLayout mContainer;
    //    public CameraViewO mCameraView;
    public CameraYzzView mCameraView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        setContentView(R.layout.activity_camera_deep5);
        mContainer = (FrameLayout) findViewById(R.id.container);


        findViewById(R.id.fab).setOnClickListener(v -> {

            takePhoto();
        });

    }

    private void takePhoto() {
        if (mCameraView != null) {
            mCameraView.takePhoto((bytes, width, height) -> {
                long end = System.currentTimeMillis();
                Bitmap bitmap = null;
                if (mCameraView.takePhotoFromGL != 1) {
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                    String fileName = "photo" + end + ".png";
//                    FileUtils.saveFile(bitmap, fileName);

                    final Bitmap finalBitmap = bitmap;
                    runOnUiThread(() -> {
                        DeepYzz5CameraActivity context = DeepYzz5CameraActivity.this;
                        ImageView imageView = new ImageView(context);
                        imageView.setImageBitmap(finalBitmap);
                        //因为读到的图上下翻转了。所以scale
                        imageView.setRotation(90);
                        new AlertDialog.Builder(context).setView(imageView).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
//                    Toast.makeText(this, "save success!!", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    //这里这个是从GL中读取现存
                    //从GL中读取的换成是上下颠倒的
                    ByteBuffer wrap = ByteBuffer.wrap(bytes);
                    String fileName = "photo" + end + ".png";
                    bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    bitmap.copyPixelsFromBuffer(wrap);
//                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), fileName);
//                    FileUtils.saveRgb2Bitmap(wrap, file, width, height);
                    final Bitmap finalBitmap = bitmap;
                    runOnUiThread(() -> {
                        DeepYzz5CameraActivity context = DeepYzz5CameraActivity.this;
                        ImageView imageView = new ImageView(context);
                        imageView.setImageBitmap(finalBitmap);
                        //因为读到的图上下翻转了。所以scale
                        imageView.setScaleY(-1);
                        new AlertDialog.Builder(context).setView(imageView).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
//                    Toast.makeText(this, "save success!!", Toast.LENGTH_SHORT).show();
                    });
                }

            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraView != null) {
            mCameraView.onPause();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraView != null) {
            mCameraView.onResume();
        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                new CameraBase1Activity.ConfirmationPermissionDialog().show(getSupportFragmentManager(), FRAGMENT_DIALOG);
                return;
            }

            startCamera();

        }
    }

    private void startCamera() {
        if (mCameraView == null) {
//            mCameraView = new CameraViewO(this);
            mCameraView = new CameraYzzView(this);
            mContainer.addView(mCameraView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }


    /**
     * Shows OK/Cancel confirmation dialog about camera permission.
     */
    public static class ConfirmationPermissionDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Fragment parent = getParentFragment();
            return new android.app.AlertDialog.Builder(getActivity())
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