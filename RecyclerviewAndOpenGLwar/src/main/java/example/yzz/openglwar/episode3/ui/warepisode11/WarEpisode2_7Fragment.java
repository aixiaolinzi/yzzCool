package example.yzz.openglwar.episode3.ui.warepisode11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode2_7Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode2_7Fragment extends Fragment {

    public WarEpisode2_7Fragment() {
        // Required empty public constructor
    }


    public static WarEpisode2_7Fragment newInstance() {
        WarEpisode2_7Fragment fragment = new WarEpisode2_7Fragment();
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
        GLView2_7 glView26 = new GLView2_7(getContext());
        return glView26;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}