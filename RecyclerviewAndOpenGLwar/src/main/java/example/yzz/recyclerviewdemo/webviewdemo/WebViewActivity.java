package example.yzz.recyclerviewdemo.webviewdemo;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import example.yzz.recyclerviewdemo.R;


/**
 *Time: 2021/4/19
 *Author:yzzCool
 *Description:WebView 最简单的使用。加载了一个百度网页。
 *
 */
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        WebView mWebView = findViewById(R.id.wv_web);
        mWebView.loadUrl("http://www.baidu.com/");
        //设置不让其跳转浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

    }


}
