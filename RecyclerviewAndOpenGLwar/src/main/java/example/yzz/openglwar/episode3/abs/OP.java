package example.yzz.openglwar.episode3.abs;

import android.content.Context;


/**
 * Time: 2021/9/14
 * Author:yzzCool
 * Description:
 * Android多媒体之GLES2战记第三集--圣火之光
 * 副本十一:The World
 * 第二关卡：简单封装
 * <p>
 * 如果图形创建在WorldRenderer中，感觉很不舒服，毕竟会有很多形状，
 * <p>
 * WorldRenderer的本意只是为了渲染以及视角的控制，并不希望图形掺杂其中
 * WorldShape可以专门绘制形状，由它统一向WorldRenderer输出形状
 * 既然WorldShape总管图形，那么操作图形，在所难免,建一个OP接口，目前只放两个方法
 */
public interface OP<T> {

    /**
     * 添加
     * @param ts 若干对象
     */
    void add(T... ts);

    /**
     * 根据id移除元素
     * @param id 索引
     */
    void remove(int id);

}
