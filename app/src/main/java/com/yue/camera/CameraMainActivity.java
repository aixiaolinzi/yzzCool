package com.yue.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yue.camera.google.preview.Camera2PreviewActivity;
import com.yue.camera.process1.CameraBase1Activity;
import com.yue.yueapp.R;

public class CameraMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_main);
    }

    public void base1(View view) {
        startActivity(new Intent(this, Camera2PreviewActivity.class));
    }

    public void base2(View view) {

    }

    public void base3(View view) {
        startActivity(new Intent(this, CameraBase1Activity.class));
    }

}