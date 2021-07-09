package com.yue.opengl.deepy5;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.Face;

import com.yue.opengl.deep5.camera.AspectRatio;
import com.yue.opengl.deep5.camera.CameraSize;

/**
 * Time:2021/6/18
 * Author:yzzCool
 * Description: 定义个相机的功能接口
 */
public interface IYzzCamera {
    boolean open(CameraId cameraId);

    /**
     * 设置画面的比例
     */
    void setAspectRatio(AspectRatio aspectRatio);

    /**
     * 开启预览
     */
    boolean preview();

    /**
     * 关闭相机
     *
     * @return
     */
    boolean close();

    /**
     * 使用SurfaceTexture 来作为预览的画面
     *
     * @param surfaceTexture
     */
    void setPreviewTexture(SurfaceTexture surfaceTexture);

    CameraSize getPreviewSize();
    CameraSize getPictureSize();
    Rect getSensorRect();


    /**
     * 添加拍照的功能
     *
     * @param callback
     */
    void takePhoto(TakePhotoCallback callback);

    /**
     * 通过回调，将对应的数据传递回去
     */
    interface TakePhotoCallback {
        void onTakePhoto(byte[] bytes, int width, int height);
    }



    /**
     * 添加拍照的功能
     *
     * @param callback
     */
    void setFaceDetectCallback(FaceDetectCallback callback);

    /**
     * 通过回调，将对应的数据传递回去
     */
    interface FaceDetectCallback {
        void onFaceDetect(Face[] faces);
    }




}
