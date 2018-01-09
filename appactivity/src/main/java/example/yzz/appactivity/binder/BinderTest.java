package example.yzz.appactivity.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;


/**
 * 描述:
 * Created by yzz on 2018/1/9.
 */

public class BinderTest {
    private void use() {
        Binder binder = new Stub();
        // 步骤1：创建Binder对象 ->>分析1

        // 步骤2：创建 IInterface 接口类 的匿名类
        // 创建前，需要预先定义 继承了IInterface 接口的接口 -->分析3
        IInterface plus = new IPlus() {
            @Override
            public int add(int a, int b) {
                return 0;
            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
        // 步骤3
        binder.attachInterface(plus,"add two int");
        // 1. 将（add two int，plus）作为（key,value）对存入到Binder对象中的一个Map<String,IInterface>对象中
        // 2. 之后，Binder对象 可根据add two int通过queryLocalIInterface（）获得对应IInterface对象（即plus）的引用，可依靠该引用完成对请求方法的调用
        // 分析完毕，跳出




    }









}

