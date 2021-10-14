package com.fei.moviedb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.fei.moviedb.R;
import com.fei.moviedb.adapter.NowPlayingAdapter;
import com.fei.moviedb.helper.Const;
import com.fei.moviedb.model.Movies;
import com.fei.moviedb.model.NowPlaying;
import com.fei.moviedb.viewmodel.MovieViewModel;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView detail_movieTitle_textView, detail_movieGenre_textView, detail_movieTagline_textView, detail_movieOverview_textView, detail_movieReleaseDate_textView, detail_movieRating_textView;
    private KenBurnsView detail_backdrop_imageView;
    private ImageView detail_poster_imageView;
    private CardView detail_backButton_cardView;
    private MovieViewModel viewModel;
    private String movie_id = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initialize();
        setAnimation();
        setListener();

    }

    private void setListener() {
        detail_backButton_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(),NowPlayingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setAnimation() {
        RandomTransitionGenerator randomTransitionGenerator = new RandomTransitionGenerator(1000, new DecelerateInterpolator());
        detail_backdrop_imageView.setTransitionGenerator(randomTransitionGenerator);
    }

    private void initialize() {
        intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);

        viewModel.setResultGetMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showMovieDetail);

        detail_backdrop_imageView = findViewById(R.id.detail_backdrop_imageView);
        detail_poster_imageView = findViewById(R.id.detail_poster_imageView);
        detail_movieTitle_textView = findViewById(R.id.detail_movieTitle_textView);
        detail_movieGenre_textView = findViewById(R.id.detail_movieGenre_textView);
        detail_movieTagline_textView = findViewById(R.id.detail_movieTagline_textView);
        detail_movieOverview_textView = findViewById(R.id.detail_movieOverview_textView);
        detail_movieReleaseDate_textView = findViewById(R.id.detail_movieReleaseDate_textView);
        detail_backButton_cardView = findViewById(R.id.detail_backButton_cardView);
        detail_movieRating_textView = findViewById(R.id.detail_movieRating_textView);

    }

    private Observer<Movies> showMovieDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            String title = movies.getTitle();
            String tagline = movies.getTagline();
            String overview = movies.getOverview();
            String releaseDate = movies.getRelease_date();
            Integer rating_int = movies.getVote_count();
            String backdrop_path = movies.getBackdrop_path().toString().trim();
            String poster_path = movies.getPoster_path().toString().trim();
            List<Movies.Genres> genreList = movies.getGenres();

            Double rating_double = new Double(rating_int);
            rating_double = rating_double/100;

            detail_movieTitle_textView.setText(title);
            detail_movieTagline_textView.setText(tagline);
            detail_movieOverview_textView.setText(overview);
            detail_movieReleaseDate_textView.setText(releaseDate);
            detail_movieRating_textView.setText(String.valueOf(rating_double));

            if(!(backdrop_path == null)){
                String full_path = Const.IMG_URL + backdrop_path;
                Glide.with(MovieDetailsActivity.this)
                        .load(full_path)
                        .into(detail_backdrop_imageView);
            }

            if(!(poster_path == null)){
                String full_path = Const.IMG_URL + poster_path;
                Glide.with(MovieDetailsActivity.this)
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

    @Override
    public void onBackPressed() {
        finish();
    }
}