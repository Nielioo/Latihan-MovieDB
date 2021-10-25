package com.fei.moviedb.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.fei.moviedb.R;
import com.fei.moviedb.helper.Const;
import com.fei.moviedb.model.Movies;
import com.fei.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button main_hit_button;
    private TextView main_show_textView;
    private TextInputLayout main_movieId_til;
    private ImageView main_show_imageView;

    private Observer<Movies> showResult = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                main_show_textView.setText("movie not available");
            } else {
                String title = movies.getTitle();
                String poster_path = movies.getPoster_path().toString();

                main_show_textView.setText(title);

                if(!(poster_path == null)){
                    String full_path = Const.IMG_URL + poster_path;
                    Glide.with(MainActivity.this)
                            .load(full_path)
                            .into(main_show_imageView);
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        main_show_textView = findViewById(R.id.main_show_textView);
        main_show_imageView = findViewById(R.id.main_show_imageView);
        main_movieId_til = findViewById(R.id.main_movieId_til);
        main_hit_button = findViewById(R.id.main_hit_button);

        main_hit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = main_movieId_til.getEditText().getText().toString().trim();
                if (movieId.isEmpty()) {
                    main_movieId_til.setError("Please fill this field!");
                } else {
                    main_movieId_til.setError(null);
//                viewModel.setResultGetMovieById("761898");
                    viewModel.setResultGetMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResult);
                }
            }
        });
    }

}