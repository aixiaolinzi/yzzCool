package example.yzz.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import example.yzz.retrofitdemo.bean.LoginBean;
import example.yzz.retrofitdemo.bean.TonkenBean;
import example.yzz.retrofitdemo.gsonconverter.MyGsonConverterFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/tools/mockapi/")
                .addConverterFactory(MyGsonConverterFactory.create())
                .build();
        LoginBean loginBean = retrofit.create(LoginBean.class);
        Call<TonkenBean> login = loginBean.getLogin();
        login.enqueue(new Callback<TonkenBean>() {
            @Override
            public void onResponse(Call<TonkenBean> call, Response<TonkenBean> response) {
                TonkenBean body = response.body();
            }

            @Override
            public void onFailure(Call<TonkenBean> call, Throwable t) {

            }
        });


        Toast.makeText(this, "测试提交", Toast.LENGTH_SHORT).show();

    }
}
