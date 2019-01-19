package com.example.fathurradhy.mocinemas.domain.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailModel {
    @SerializedName("poster_path")
    @Expose
    String posterPath;

    @SerializedName("vote_average")
    @Expose
    String voteAverage;

    @SerializedName("title")
    @Expose
    private
    String title;

    @SerializedName("runtime")
    @Expose
    String runtime;

    @SerializedName("overview")
    @Expose
    String overview;

    @SerializedName("genres")
    @Expose
    List<DetailModelGenre> genres;

    public String getPosterPath() {
        return posterPath;
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

    public String getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public List<DetailModelGenre> getGenres() {
        return genres;
    }

}
