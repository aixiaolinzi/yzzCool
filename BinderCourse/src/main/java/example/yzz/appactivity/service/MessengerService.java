package example.yzz.appactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.Nullable;


/**
 * 描述:
 * Created by yzz on 2018/1/8.
 */

public class MessengerService extends Service {

    class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //不同的情况处理不同的逻辑
                case 1:

                    break;
            }
        }
    }
    final Messenger mMessenger = new Messenger(new MessageHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
