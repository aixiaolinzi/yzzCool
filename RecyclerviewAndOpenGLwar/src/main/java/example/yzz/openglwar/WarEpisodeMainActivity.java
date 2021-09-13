package example.yzz.openglwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import example.yzz.openglwar.episode1.WarEpisode1Activity;
import example.yzz.openglwar.episode2.WarEpisode2Activity;
import example.yzz.recyclerviewdemo.R;

public class WarEpisodeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_episode_main);
    }

    public void onClick1(View view) {
        startActivity(new Intent(this, WarEpisode1Activity.class));
    }

    public void onClick2(View view) {
        startActivity(new Intent(this, WarEpisode2Activity.class));
    }
}