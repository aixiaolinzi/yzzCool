#include <jni.h>
#include <string>



extern "C"
JNIEXPORT jstring JNICALL
Java_com_yue_openGLC_gles1_OpenGLES1Activity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    // TODO: implement stringFromJNI()
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}