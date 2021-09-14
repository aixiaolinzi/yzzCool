package example.yzz.openglwar.episode3.ui.warepisode11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode3_11Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode3_11Fragment extends Fragment {

    public WarEpisode3_11Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode3_11Fragment newInstance() {
        WarEpisode3_11Fragment fragment = new WarEpisode3_11Fragment();
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
        WorldView3_11 worldView3_11 = new WorldView3_11(getContext());
        return worldView3_11;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}