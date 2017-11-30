package example.yzz.appeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.yzz.eventbus.EventBus;
import example.yzz.eventbus.Subscribe;
import example.yzz.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new RunText()).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String ss) {
        Log.e(TAG, "得到的值++" + ss);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    class RunText implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                EventBus.getDefault().post("love");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
