package com.yue.openGLC.gles1;

public
/**
 *Time:2021/4/13
 *Author:yzzCool
 *Description:
 */
class MyNativeRender {

    static {
        System.loadLibrary("native-render");
    }

    public native void native_OnInit();

    public native void native_OnUnInit();

    public native void native_SetImageData(int format, int width, int height, byte[] bytes);

    public native void native_OnSurfaceCreated();

    public native void native_OnSurfaceChanged(int width, int height);

    public native void native_OnDrawFrame();

}
