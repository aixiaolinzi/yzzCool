package example.yzz.retrofitdemo.bean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 描述:
 * Created by yzz on 2018/5/3.
 */

public interface LoginBean {
    @GET("4130/look")
    Call<TonkenBean> getLogin();
}
