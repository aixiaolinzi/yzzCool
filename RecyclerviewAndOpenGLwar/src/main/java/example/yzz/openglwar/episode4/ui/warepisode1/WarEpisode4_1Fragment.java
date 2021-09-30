package example.yzz.openglwar.episode4.ui.warepisode1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode4_1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode4_1Fragment extends Fragment {

    public WarEpisode4_1Fragment() {
        // Required empty public constructor
    }

    public static WarEpisode4_1Fragment newInstance() {
        WarEpisode4_1Fragment fragment = new WarEpisode4_1Fragment();
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
        WorldView4_1 worldView41 = new WorldView4_1(getContext());
        return worldView41;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}