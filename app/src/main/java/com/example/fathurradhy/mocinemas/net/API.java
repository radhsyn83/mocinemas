package com.example.fathurradhy.mocinemas.net;

import com.example.fathurradhy.mocinemas.domain.model.detail.DetailModel;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("movie/now_playing")
    Call<MoviesModel> getNowPlaying(
            @Query("api_key") String api
    );

    @GET("movie/upcoming")
    Call<MoviesModel> getComingSoon(
            @Query("api_key") String api
    );

    @GET("search/movie")
    Call<MoviesModel> getSearch(
            @Query("api_key") String api,
            @Query("query") String query
    );

    @GET("movie/{id}")
    Call<DetailModel> getDetail(
            @Path("id") String id,
            @Query("api_key") String api
    );
}
