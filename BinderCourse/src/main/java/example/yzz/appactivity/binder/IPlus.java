package example.yzz.appactivity.binder;

import android.os.IInterface;

/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public interface IPlus extends IInterface {
    // 继承自IInterface接口->>分析4
    // 定义需要实现的接口方法，即Client进程需要调用的方法
    public int add(int a, int b);
// 返回步骤2
}
