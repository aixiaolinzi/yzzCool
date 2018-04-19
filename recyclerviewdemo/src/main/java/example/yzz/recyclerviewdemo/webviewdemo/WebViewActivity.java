package example.yzz.recyclerviewdemo.webviewdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import example.yzz.recyclerviewdemo.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mWebView = findViewById(R.id.wv_web);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        mWebView.addJavascriptInterface(new JavaScriptInterface(this), "ShowMessageFromWebView");
        mWebView.loadUrl("http://musicmember.skyware.com.cn/");


    }


    public class JavaScriptInterface {

        Context context;

        JavaScriptInterface(Context context) {
            this.context = context;
        }

        //在js中调用 androidjs.showInfoFromJs("I'M FROM JS!!!")就会出发此方法
        @JavascriptInterface
        public Object getUserInfo(String  s) {

            return "{\n" +
                    "    \"iconUrl\": \"BeJson\",\n" +
                    "    \"nickName\": \"bejson.com\",\n" +
                    "    \"page\": \"8ce3dfd0c261564905904811a3a52bbe\"\n" +
                    "}";
        }
    }


}