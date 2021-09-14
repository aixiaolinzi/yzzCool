package example.yzz.openglwar.episode3.ui.warepisode10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode3_10Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode3_10Fragment extends Fragment {

    public WarEpisode3_10Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode3_10Fragment newInstance() {
        WarEpisode3_10Fragment fragment = new WarEpisode3_10Fragment();
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
        WorldView3_10 worldView3_10 = new WorldView3_10(getContext());
        return worldView3_10;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}