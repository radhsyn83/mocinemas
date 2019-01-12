package com.example.fathurradhy.mocinemas.domain.model.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesModelResult {
    @SerializedName("vote_count")
    @Expose
    String voteCount;

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("video")
    @Expose
    String video;

    @SerializedName("vote_average")
    @Expose
    String voteAverage;

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("popularity")
    @Expose
    String popularity;

    @SerializedName("poster_path")
    @Expose
    String posterPath;

    @SerializedName("original_language")
    @Expose
    String originalLanguage;

    @SerializedName("original_title")
    @Expose
    String originalTitle;

    @SerializedName("genre_ids")
    @Expose
    List<String> genreIds;

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }
}
