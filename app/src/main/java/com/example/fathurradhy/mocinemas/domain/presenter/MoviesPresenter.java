package com.example.fathurradhy.mocinemas.domain.presenter;

interface MoviesPresenter {
    void getNowPlaying(String api);
    void getComingSoon(String api);
    void searchMovies(String api, String query);
}
