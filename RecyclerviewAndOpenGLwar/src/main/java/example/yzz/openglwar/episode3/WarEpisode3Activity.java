package example.yzz.openglwar.episode3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import example.yzz.openglwar.episode3.ui.warepisode10.WarEpisode3_10Fragment;
import example.yzz.openglwar.episode3.ui.warepisode11.WarEpisode3_11Fragment;
import example.yzz.openglwar.episode3.ui.warepisode12.WarEpisode3_12Fragment;
import example.yzz.openglwar.episode3.ui.warepisode9.WarEpisode3_9Fragment;
import example.yzz.recyclerviewdemo.R;

public class WarEpisode3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.war_episode1_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WarEpisode3_12Fragment.newInstance())
                    .commitNow();
        }
    }
}