package example.yzz.openglwar.episode3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import example.yzz.openglwar.episode3.ui.warepisode9.WarEpisode3_9Fragment;
import example.yzz.recyclerviewdemo.R;

public class WarEpisode3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.war_episode1_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WarEpisode3_9Fragment.newInstance())
                    .commitNow();
        }
    }
}