package example.yzz.recyclerviewdemo.okhttptest;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


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

    private String TAG = "YzzLogger HTTPActivity测试";
    private WebView webView_http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        getDatasync();

        webView_http = findViewById(R.id.web_http_activity);



        WebSettings settings = webView_http.getSettings();
        // 设置WebView支持JavaScript
        settings.setJavaScriptEnabled(true);
        //支持自动适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);  //支持放大缩小
        settings.setBuiltInZoomControls(true); //显示缩放按钮
        settings.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        settings.setAllowFileAccess(false);
        settings.setSaveFormData(false);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置不让其跳转浏览器
        webView_http.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView_http.setWebChromeClient(new WebChromeClient());



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
                        final String stringBody = response.body().string();
                        Log.e(TAG, "res==" + stringBody);

                        webView_http.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "webView res==" + stringBody);
                                webView_http.loadData(stringBody,"text/html" , "utf-8");
                            }
                        });
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
