package com.fei.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fei.moviedb.R;
import com.fei.moviedb.adapter.NowPlayingAdapter;
import com.fei.moviedb.helper.ItemClickSupport;
import com.fei.moviedb.model.NowPlaying;
import com.fei.moviedb.view.activities.NowPlayingActivity;
import com.fei.moviedb.viewmodel.MovieViewModel;

public class NowPlayingFragment extends Fragment {

    private RecyclerView nowPlayingFragment_RecyclerView;
    private MovieViewModel viewModel;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        nowPlayingFragment_RecyclerView= view.findViewById(R.id.nowPlayingFragment_RecyclerView);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.setResultGetNowPlaying();
        viewModel.getResultGetNowPlaying().observe(getActivity(), showNowPlaying);

        return view;
    }


    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            nowPlayingFragment_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setNowPlayingList(nowPlaying.getResults());
            nowPlayingFragment_RecyclerView.setAdapter(adapter);

            //untuk click lama
//            ItemClickSupport.addTo(nowPlayingFragment_RecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                    return false;
//                }
//            });

            //untuk click sekali, bisa buat passing data
            ItemClickSupport.addTo(nowPlayingFragment_RecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    //bundle yang di nowPlayingAdapter bisa dipindah ke sini
                }
            });

        }
    };

}