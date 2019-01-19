package com.example.fathurradhy.mocinemas.domain.model.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesModel {
    @SerializedName("results")
    @Expose
    List<MoviesModelResult> result;

    public List<MoviesModelResult> getResult() {
        return result;
    }
}
