package example.yzz.openglwar.utils;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 *Time: 2021/9/13
 *Author:yzzCool
 *Description: 副本二---龙之怒色 2.第二关卡：资源文件的读取
 * 添加的资源。
 */
public class GLUtils {

    //从脚本中加载shader内容的方法
    public static int loadShaderAssets(Context ctx, int type, String name) {
        String result = null;
        try {
            InputStream in = ctx.getAssets().open(name);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            result = new String(buff, "UTF-8");
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadShader(type, result);
    }

    /**
     * 加载作色器
     *
     * @param type       着色器类型    顶点着色 {@link GLES20.GL_VERTEX_SHADER}
     *                   片元着色 {@link GLES20.GL_FRAGMENT_SHADER}
     * @param shaderCode 着色代码
     * @return 作色器
     */
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);//创建着色器
        if (shader == 0) {//加载失败直接返回
            return 0;
        }
        GLES20.glShaderSource(shader, shaderCode);//加载着色器源代码
        GLES20.glCompileShader(shader);//编译
        return checkCompile(type, shader);
    }

    /**
     * 检查shader代码是否编译成功
     *
     * @param type   着色器类型
     * @param shader 着色器
     * @return 着色器
     */
    private static int checkCompile(int type, int shader) {
        int[] compiled = new int[1];//存放编译成功shader数量的数组
        //获取Shader的编译情况
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {//若编译失败则显示错误日志并
            Log.e("ES20_COMPILE_ERROR",
                    "Could not compile shader " + type + ":" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);//删除此shader
            shader = 0;
        }
        return shader;
    }

    /**
     * float数组缓冲数据
     * @param vertexs 顶点
     * @return 获取浮点形缓冲数据
     */
    public static FloatBuffer getFloatBuffer(float[] vertexs) {
        FloatBuffer buffer;
        ///每个浮点数:坐标个数* 4字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexs.length * 4);
        //使用本机硬件设备的字节顺序
        qbb.order(ByteOrder.nativeOrder());
        // 从字节缓冲区创建浮点缓冲区
        buffer = qbb.asFloatBuffer();
        // 将坐标添加到FloatBuffer
        buffer.put(vertexs);
        //设置缓冲区以读取第一个坐标
        buffer.position(0);
        return buffer;
    }

    /**
     * short数组缓冲数据
     * @param vertexs short 数组
     * @return 获取short缓冲数据
     */
    public static ShortBuffer getShortBuffer(short[] vertexs) {
        ShortBuffer buffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(vertexs.length * 2);
        qbb.order(ByteOrder.nativeOrder());
        buffer = qbb.asShortBuffer();
        buffer.put(vertexs);
        buffer.position(0);
        return buffer;
    }



}
