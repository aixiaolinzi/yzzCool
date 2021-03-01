package example.yzz.recyclerviewdemo.okhttptest;


import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import example.yzz.recyclerviewdemo.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class HttpActivity extends AppCompatActivity {

    private String TAG = "HTTPActivity测试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        getDatasync();


    }


    public void getDatasync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.e(TAG, "response.code()==" + response.code());
                        Log.e(TAG, "response.message()==" + response.message());
                        Log.e(TAG, "res==" + response.body().string());
                        InputStream inputStream = response.body().byteStream();
                        byte[] bytes = response.body().bytes();
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDataPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {


                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("")
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });




                RequestBody formBody = new FormBody.Builder()
                        .add("platform", "android")
                        .add("name", "bug")
                        .build();


            }
        }).start();
    }

}
