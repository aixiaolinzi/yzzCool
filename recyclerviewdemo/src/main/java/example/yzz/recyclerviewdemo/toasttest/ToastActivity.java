package example.yzz.recyclerviewdemo.toasttest;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import example.yzz.recyclerviewdemo.R;

public class ToastActivity extends AppCompatActivity {

    private TextView tv_text;
    private TextView tv_text1;
    private TextView tv_text2;
    private TextView tv_text3;
    private TextView tv_text4;
    private TextView tv_text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);


        tv_text = findViewById(R.id.tv_text);
        tv_text1 = findViewById(R.id.tv_text1);
        tv_text2 = findViewById(R.id.tv_text2);
        tv_text3 = findViewById(R.id.tv_text3);
        tv_text4 = findViewById(R.id.tv_text4);
        tv_text5 = findViewById(R.id.tv_text5);



        //下划线,两种实现
        tv_text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_text1.getPaint().setUnderlineText(true);

        //抗锯齿
        tv_text2.getPaint().setAntiAlias(true);

        //中划线
        tv_text3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        // 设置中划线并加清晰 
        tv_text4.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);

        //字体加粗
        tv_text5.getPaint().setFakeBoldText(true);


    }

    public void btn_1(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("你的名字");
        alertDialog.show();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e("TAG", "搜索页面+");
                dialog.cancel();
            }
        });


    }

    public void btn_2(View v) {
        Toast toast = new Toast(this);
        TextView textView = new TextView(this);
        textView.setText("自定义1");
        toast.setView(textView);
        toast.show();
    }

    public void btn_3(View v) {
        //LayoutInflater inflate = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Toast.makeText(this, "", Toast.LENGTH_SHORT).getView();
        Toast sToast = new Toast(this);
        sToast.setView(view);

        sToast.setText("自定义2");
        sToast.setDuration(Toast.LENGTH_SHORT);
        sToast.show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("TAG", "搜索页面+" + keyCode + "++event++" + event);
        return super.onKeyDown(keyCode, event);
    }
}
