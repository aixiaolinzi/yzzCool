package example.yzz.recyclerviewdemo.volleytest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import example.yzz.recyclerviewdemo.R;

public class VolleyActivity extends AppCompatActivity {

    private static String TAG = "YzzLogger Volley测试";
    private ImageView imageView;
    private TextView tv_Volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        imageView = findViewById(R.id.imageView);
        tv_Volley = findViewById(R.id.tv_volley);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageRequest imageRequest = new ImageRequest("http://image.97ting.com/album/100/1000315-JPG-1000X1000-ALBUM.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.e(TAG, String.valueOf(response.getHeight()));
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);

        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET, "http://flash.weather.com.cn/wmaps/xml/china.xml", new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                if ("city".equals(nodeName)) {
                                    String pName = response.getAttributeValue(0);
                                    Log.e(TAG, "pName is " + pName);
                                    stringBuilder.append("pName is").append(pName).append("\n");
                                }
                                break;
                        }
                        eventType = response.next();
                        tv_Volley.setText(stringBuilder.toString());

                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error is " + error.toString());
            }
        });
        requestQueue.add(xmlRequest);

    }
}
