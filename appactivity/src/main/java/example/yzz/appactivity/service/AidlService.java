package example.yzz.appactivity.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import example.yzz.appactivity.IMyAidlInterface;

/**
 * 描述:
 * Created by yzz on 2018/1/8.
 */

public class AidlService extends Service {
    private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getDate() throws RemoteException {
            return 0;
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return 0;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
