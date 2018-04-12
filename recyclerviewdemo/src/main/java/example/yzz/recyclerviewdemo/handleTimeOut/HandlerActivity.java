package example.yzz.recyclerviewdemo.handleTimeOut;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import example.yzz.recyclerviewdemo.R;

public class HandlerActivity extends AppCompatActivity {

    private static class MyHandler extends Handler {
        private final WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity activity) {
            mActivity = new WeakReference<HandlerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
      /* ... */
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);


    }
}
