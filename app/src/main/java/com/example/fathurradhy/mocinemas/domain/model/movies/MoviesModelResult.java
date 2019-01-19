package com.example.fathurradhy.mocinemas.domain.model.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesModelResult {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("vote_average")
    @Expose
    String voteAverage;

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("poster_path")
    @Expose
    String posterPath;

    @SerializedName("release_date")
    @Expose
    String releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
