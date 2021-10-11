package com.fei.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fei.moviedb.model.Movies;
import com.fei.moviedb.model.NowPlaying;
import com.fei.moviedb.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Begin of viewModel getMovieById

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void setResultGetMovieById(String movieId){
        resultGetMovieById =  repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }

    //==End of viewModel getMovieById

    //==Begin of viewModel getNowPlaying

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void setResultGetNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewModel getNowPlaying

}
