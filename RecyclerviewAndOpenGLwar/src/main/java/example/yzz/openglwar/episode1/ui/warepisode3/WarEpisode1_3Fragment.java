package example.yzz.openglwar.episode1.ui.warepisode3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarEpisode1_3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarEpisode1_3Fragment extends Fragment {

    public WarEpisode1_3Fragment() {
        // Required empty public constructor
    }

    public static WarEpisode1_3Fragment newInstance() {
        WarEpisode1_3Fragment fragment = new WarEpisode1_3Fragment();
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
        GLView1_3 glView12 = new GLView1_3(getContext());
        return glView12;
        //return inflater.inflate(R.layout.fragment_war_episode1_2, container, false);
    }
}