package com.yue.yueapp;


import androidx.annotation.NonNull;

import io.reactivex.functions.Consumer;

/**
 * 错误情况下。错误日志的处理和打印
 * @author yzz
 * Created on 2017/12/12 17:44
 */

public class ErrorAction {

    @NonNull
    public static Consumer<Throwable> error() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if (BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
