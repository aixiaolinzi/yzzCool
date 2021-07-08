package com.yue.opengl.deepy5;

public enum CameraId {
    /**
     * rear camera id
     */
    REAR("0"),

    /**
     * front camera id
     */
    FRONT("1"),

    /**
     * ultra wide using camera id 2
     */
    ULTRAWIDE("2"),

    /**
     * double camera bokeh using camera id 4
     */
    BOKEH("4");


    private String mCameraId;

    CameraId(String cameraId) {
        mCameraId = cameraId;
    }

    public String getCameraId() {
        return mCameraId;
    }

}
