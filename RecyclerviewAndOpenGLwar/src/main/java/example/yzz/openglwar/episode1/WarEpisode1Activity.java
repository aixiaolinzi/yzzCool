package example.yzz.openglwar.episode1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import example.yzz.openglwar.episode1.ui.warepisode1.WarEpisode1_1Fragment;
import example.yzz.openglwar.episode1.ui.warepisode2.WarEpisode1_2Fragment;
import example.yzz.recyclerviewdemo.R;

public class WarEpisode1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.war_episode1_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WarEpisode1_2Fragment.newInstance())
                    .commitNow();
        }
    }
}