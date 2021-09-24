package example.yzz.openglwar.episode3.abs;

/**
 * Time:2021/9/13
 * Author:yzzCool
 * Description:
 * 1. 追加VERTEX_COO和COLOR_COO 辅助坐标轴。
 */
public class Cons {
    //维度：独立参数的数目
    public static final int DIMENSION_2 = 2;//2维度
    public static final int DIMENSION_3 = 3;//3维度
    public static final int DIMENSION_4 = 4;//4维度


    public static final float[] VERTEX_COO = {//坐标轴
            0.0f, 0.0f, 0.0f,//Z轴
            0.0f, 0.0f, 0.5f,
            0.0f, 0.0f, 0.0f,//X轴
            0.5f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,//Y轴
            0.0f, 0.5f, 0.0f,
    };
    public static final float[] COLOR_COO = {//坐标轴颜色
            0.0f, 0.0f, 1.0f, 1.0f,//Z轴:蓝色
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f,//X轴：黄色
            1.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.3f, 0.0f, 1.0f,//Y轴：绿色,修改为0.3 为了区分
            0.0f, 0.3f, 0.0f, 1.0f,
    };

}
