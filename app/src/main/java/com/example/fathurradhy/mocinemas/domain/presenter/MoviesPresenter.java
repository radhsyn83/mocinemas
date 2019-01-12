package com.example.fathurradhy.mocinemas.domain.presenter;

interface MoviesPresenter {
    void getPopular(String api);
    void searchMovies(String api, String query);
}
