package com.fei.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.fei.moviedb.R;
import com.fei.moviedb.adapter.NowPlayingAdapter;
import com.fei.moviedb.model.NowPlaying;
import com.fei.moviedb.viewmodel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView nowPlaying_RecyclerView;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        nowPlaying_RecyclerView= findViewById(R.id.nowPlaying_RecyclerView);
        viewModel = new ViewModelProvider(NowPlayingActivity.this).get(MovieViewModel.class);
        viewModel.setResultGetNowPlaying();
        viewModel.getResultGetNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);

    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            nowPlaying_RecyclerView.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setNowPlayingList(nowPlaying.getResults());
            nowPlaying_RecyclerView.setAdapter(adapter);

        }
    };

}