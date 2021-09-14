package example.yzz.openglwar.episode3.ui.warepisode9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode3_9Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode3_9Fragment extends Fragment {

    public WarEpisode3_9Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode3_9Fragment newInstance() {
        WarEpisode3_9Fragment fragment = new WarEpisode3_9Fragment();
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
        WorldView3_9 glView12 = new WorldView3_9(getContext());
        return glView12;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}