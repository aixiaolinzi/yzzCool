#include <jni.h>
#include <string>
#include <android/asset_manager_jni.h>
#include <android/log.h>


#define LOG_TAG "ndk-build"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)


extern "C"
JNIEXPORT void JNICALL
Java_com_yue_openGLC_guide1_RendererJNI_glesInit(JNIEnv *env, jobject thiz) {
// TODO: implement glesInit()
    std::string hello = "Hello from C++";


    LOGI("YzzLogger pAssetManager is null!");
}


extern "C"
JNIEXPORT void JNICALL
Java_com_yue_openGLC_guide1_RendererJNI_glesRender(JNIEnv *env, jobject thiz) {
// TODO: implement glesRender()

    LOGI("YzzLogger 1233333!");
}


extern "C"
JNIEXPORT void JNICALL
Java_com_yue_openGLC_guide1_RendererJNI_glesResize(JNIEnv *env, jobject thiz, jint width,
                                                   jint height
) {
// TODO: implement glesResize()
    LOGI("YzzLogger 22222222222222!");
}



extern "C"
JNIEXPORT void JNICALL
Java_com_yue_openGLC_guide1_RendererJNI_readShaderFile(JNIEnv *env, jobject thiz,
                                                       jobject asset_mgr) {
// TODO: implement readShaderFile()
    LOGI("YzzLogger 3333333333333!");
}







