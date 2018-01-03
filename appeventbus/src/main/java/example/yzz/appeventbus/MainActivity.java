package example.yzz.appeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.yzz.eventbus.EventBus;
import example.yzz.eventbus.Subscribe;
import example.yzz.eventbus.ThreadMode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.ByteString;

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initMockServer();
                        initWsClient("ws://" + mMockWebServer.getHostName() + ":" + mMockWebServer.getPort() + "/");
                    }
                }).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 创建全局对象 MockWebServer
    private static final MockWebServer mMockWebServer = new MockWebServer();
    // 创建对象 服务端响应对象
    private MockResponse mMockResponse = null;

    // 创建监听回调
    public MockResponse createResponse() {
        return new MockResponse().withWebSocketUpgrade(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("server onOpen: request header:" + response.request().headers() + " response header:" + response.headers() + " response:" + response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String string) {
                System.out.println("server onMessage: message:" + string);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("server onClosing: code:" + code + " reason:" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("server onClosed code:" + code + " reason:" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("server onFailure");
            }
        });
    }

    private void initMockServer() {
        mMockResponse = createResponse();
        mMockWebServer.enqueue(mMockResponse);
    }


    // 新建client
    private static final OkHttpClient mClient = new OkHttpClient.Builder().build();
    private WebSocket mWebSocket;

    // 创建请求回调
    private WebSocketListener createListener() {
        return new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                mWebSocket = webSocket;
                System.out.println("client onOpen");
                System.out.println("client request header:" + response.request().headers());
                System.out.println("client response header:" + response.headers());
                System.out.println("client response:" + response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println("client onMessage");
                System.out.println("message:" + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("client onClosing");
                System.out.println("code:" + code + " reason:" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("client onClosed");
                System.out.println("code:" + code + " reason:" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("client onFailure");
            }
        };
    }

    private void initWsClient(String wsUrl) {
        Request request = new Request.Builder().url(wsUrl).build();
        WebSocketListener wbListener = createListener();
        mClient.newWebSocket(request, wbListener);
    }


}
