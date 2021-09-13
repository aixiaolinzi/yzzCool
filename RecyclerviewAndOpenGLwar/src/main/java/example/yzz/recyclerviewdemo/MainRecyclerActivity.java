package example.yzz.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import example.yzz.recyclerviewdemo.okhttptest.HttpActivity;
import example.yzz.recyclerviewdemo.shape.ShapeActivity;
import example.yzz.recyclerviewdemo.socket.SocketTestActivity;
import example.yzz.recyclerviewdemo.volleytest.VolleyActivity;
import example.yzz.recyclerviewdemo.webviewdemo.WebViewActivity;

public class MainRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);
    }

    public void recycler_tv1(View view) {
        //跳转到RecyclerViewActivity
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void recycler_tv2(View view) {
        //跳转到HttpActivity
        startActivity(new Intent(this, HttpActivity.class));
    }

    public void recycler_tv3(View view) {
        //跳转到SocketTestActivity
        startActivity(new Intent(this, SocketTestActivity.class));
    }

    public void recycler_tv4(View view) {
        //跳转到VolleyActivity
        startActivity(new Intent(this, VolleyActivity.class));
    }

    public void recycler_tv5(View view) {
        //跳转到WebViewActivity
        startActivity(new Intent(this, WebViewActivity.class));
    }

    public void recycler_tv6(View view) {
        //跳转到ShapeActivity
        startActivity(new Intent(this, ShapeActivity.class));
    }
}