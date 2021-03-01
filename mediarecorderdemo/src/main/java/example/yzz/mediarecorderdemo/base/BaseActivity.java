package example.yzz.mediarecorderdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 描述:
 * Created by yzz on 2018/3/27.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowView();
        initViews();
        initEvents();
    }

    protected abstract void setWindowView();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Activity act, Class cls) {
        Intent intent = new Intent(act, cls);
        act.startActivity(intent);
    }
}
