package example.yzz.recyclerviewdemo;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * 描述:
 * Created by yzz on 2018/3/27.
 */

public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> mListener;
    private Class<T> tClass;
    private final Gson gson;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> mListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.tClass = clazz;
        gson = new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T t = gson.fromJson(data, tClass);
            mListener.onResponse(t);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }
}
