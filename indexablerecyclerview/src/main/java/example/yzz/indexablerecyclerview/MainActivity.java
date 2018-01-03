package example.yzz.indexablerecyclerview;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import example.yzz.indexablerecyclerview.city.PickCityActivity;
import example.yzz.indexablerecyclerview.contact.PickContactActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_pick_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PickCityActivity.class));
            }
        });
        findViewById(R.id.btn_pick_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PickContactActivity.class));
            }
        });


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.e("TAG", "seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Log.e("TAG", "seconds remaining:onFinishonFinishonFinishonFinishonFinishonFinish ");
            }
        }.start();


        Double.compare(3, 2);
    }
}