package example.yzz.appactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import example.yzz.appactivity.IMyAidlInterface;

/**
 * 描述:AIDL的测试使用
 * Created by yzz on 2018/1/8.
 */

public class RemoteService extends Service {

    private final IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getDate() throws RemoteException {
            return 0;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
