package example.yzz.openglwar.episode2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import example.yzz.openglwar.episode2.ui.warepisode5.WarEpisode2_5Fragment;
import example.yzz.openglwar.episode2.ui.warepisode6.WarEpisode2_6Fragment;
import example.yzz.openglwar.episode2.ui.warepisode7.WarEpisode2_7Fragment;
import example.yzz.recyclerviewdemo.R;

public class WarEpisode2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.war_episode1_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WarEpisode2_7Fragment.newInstance())
                    .commitNow();
        }
    }
}