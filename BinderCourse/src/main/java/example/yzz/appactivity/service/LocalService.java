package example.yzz.appactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import androidx.annotation.Nullable;

import java.util.Random;

/**
 * 描述:
 * Created by yzz on 2018/1/8.
 */

public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * method for clients
     */
    public int getRandomNumber() {
        return new Random().nextInt(100);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}
