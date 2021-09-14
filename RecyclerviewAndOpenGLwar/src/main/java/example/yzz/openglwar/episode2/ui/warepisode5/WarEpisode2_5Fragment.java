package example.yzz.openglwar.episode2.ui.warepisode5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode2_5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode2_5Fragment extends Fragment {

    public WarEpisode2_5Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode2_5Fragment newInstance() {
        WarEpisode2_5Fragment fragment = new WarEpisode2_5Fragment();
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
        GLView2_5 glView12 = new GLView2_5(getContext());
        return glView12;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}