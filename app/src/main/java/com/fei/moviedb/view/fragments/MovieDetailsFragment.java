package com.fei.moviedb.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fei.moviedb.R;
import com.fei.moviedb.helper.Const;
import com.fei.moviedb.model.Movies;
import com.fei.moviedb.view.activities.MovieDetailsActivity;
import com.fei.moviedb.view.activities.NowPlayingActivity;
import com.fei.moviedb.viewmodel.MovieViewModel;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.List;

public class MovieDetailsFragment extends Fragment {

    private TextView detail_movieTitle_textView, detail_movieGenre_textView, detail_movieTagline_textView, detail_movieOverview_textView, detail_movieReleaseDate_textView, detail_movieRating_textView;
    private KenBurnsView detail_backdrop_imageView;
    private ImageView detail_poster_imageView;
    private CardView detail_backButton_cardView;
    private MovieViewModel viewModel;
    private String movie_id = "";
    private Intent intent;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        initialize();
        setAnimation();
//        setListener();

        return view;
    }

    private void setListener() {
//        private void setListener() {
//            detail_backButton_cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//        }
    }

    private void setAnimation() {
        RandomTransitionGenerator randomTransitionGenerator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        detail_backdrop_imageView.setTransitionGenerator(randomTransitionGenerator);
    }

    private void initialize() {
        String movieId = getArguments().getString("movieId");

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

        viewModel.setResultGetMovieById(movieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showMovieDetail);

        detail_backdrop_imageView = view.findViewById(R.id.detail_backdrop_imageView);
        detail_poster_imageView = view.findViewById(R.id.detail_poster_imageView);
        detail_movieTitle_textView = view.findViewById(R.id.detail_movieTitle_textView);
        detail_movieGenre_textView = view.findViewById(R.id.detail_movieGenre_textView);
        detail_movieTagline_textView = view.findViewById(R.id.detail_movieTagline_textView);
        detail_movieOverview_textView = view.findViewById(R.id.detail_movieOverview_textView);
        detail_movieReleaseDate_textView = view.findViewById(R.id.detail_movieReleaseDate_textView);
        detail_backButton_cardView = view.findViewById(R.id.detail_backButton_cardView);
        detail_movieRating_textView = view.findViewById(R.id.detail_movieRating_textView);
    }

    private Observer<Movies> showMovieDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            String title = movies.getTitle();
            String tagline = movies.getTagline();
            String overview = movies.getOverview();
            String releaseDate = movies.getRelease_date();
            String rating = String.valueOf(movies.getVote_average());
            String backdrop_path = movies.getBackdrop_path().toString().trim();
            String poster_path = movies.getPoster_path().toString().trim();
            List<Movies.Genres> genreList = movies.getGenres();

            detail_movieTitle_textView.setText(title);
            detail_movieTagline_textView.setText(tagline);
            detail_movieOverview_textView.setText(overview);
            detail_movieReleaseDate_textView.setText(releaseDate);
            detail_movieRating_textView.setText(String.valueOf(rating));

            if(!(backdrop_path == null)){
                String full_path = Const.IMG_URL + backdrop_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(detail_backdrop_imageView);
            }

            if(!(poster_path == null)){
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(getActivity())
                        .load(full_path)
                        .into(detail_poster_imageView);
            }

            for (int i = 0; i < genreList.size(); i++) {
                Movies.Genres g = genreList.get(i);

                if (i < genreList.size() - 1) {
                    detail_movieGenre_textView.append(g.getName() + " . ");
                } else {
                    detail_movieGenre_textView.append(g.getName());
                }

            }

        }
    };

}