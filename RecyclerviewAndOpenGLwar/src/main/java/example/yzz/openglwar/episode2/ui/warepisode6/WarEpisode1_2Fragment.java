package example.yzz.openglwar.episode2.ui.warepisode6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode1_2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode1_2Fragment extends Fragment {

    public WarEpisode1_2Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode1_2Fragment newInstance() {
        WarEpisode1_2Fragment fragment = new WarEpisode1_2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GLView1_2 glView12 = new GLView1_2(getContext());
        return glView12;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}