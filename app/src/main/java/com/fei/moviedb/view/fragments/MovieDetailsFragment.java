package com.fei.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fei.moviedb.R;

public class MovieDetailsFragment extends Fragment {

    private TextView movieDetailsFragment_textView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        movieDetailsFragment_textView = view.findViewById(R.id.movieDetailsFragment_textView);

        String movieId = getArguments().getString("movieId");
        movieDetailsFragment_textView.setText(movieId);

        return view;
    }
}