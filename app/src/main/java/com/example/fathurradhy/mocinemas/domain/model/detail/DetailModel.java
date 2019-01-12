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
    String title;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("release_date")
    @Expose
    String releaseDate;

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

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<DetailModelGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<DetailModelGenre> genres) {
        this.genres = genres;
    }
}
