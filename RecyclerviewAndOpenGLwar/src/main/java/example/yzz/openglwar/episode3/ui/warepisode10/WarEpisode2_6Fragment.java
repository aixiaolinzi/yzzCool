package example.yzz.openglwar.episode3.ui.warepisode10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode2_6Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode2_6Fragment extends Fragment {

    public WarEpisode2_6Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode2_6Fragment newInstance() {
        WarEpisode2_6Fragment fragment = new WarEpisode2_6Fragment();
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
        GLView2_6 glView26 = new GLView2_6(getContext());
        return glView26;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}