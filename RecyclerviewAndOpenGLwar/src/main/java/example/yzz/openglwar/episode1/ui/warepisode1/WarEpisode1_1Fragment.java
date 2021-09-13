package example.yzz.openglwar.episode1.ui.warepisode1;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.yzz.recyclerviewdemo.R;


public class WarEpisode1_1Fragment extends Fragment {

    private WarEpisode1_1ViewModel mViewModel;

    public static WarEpisode1_1Fragment newInstance() {
        return new WarEpisode1_1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       GLView glView =  new GLView(getContext());
//        return inflater.inflate(R.layout.war_episode1_1_fragment, container, false);
        return glView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WarEpisode1_1ViewModel.class);
        // TODO: Use the ViewModel
    }

}