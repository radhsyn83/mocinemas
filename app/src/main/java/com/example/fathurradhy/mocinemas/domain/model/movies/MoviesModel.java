package com.example.fathurradhy.mocinemas.domain.model.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesModel {
    @SerializedName("page")
    @Expose
    String page;

    @SerializedName("total_results")
    @Expose
    String totalResult;

    @SerializedName("total_pages")
    @Expose
    String totalPages;

    @SerializedName("results")
    @Expose
    List<MoviesModelResult> result;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<MoviesModelResult> getResult() {
        return result;
    }

    public void setResult(List<MoviesModelResult> result) {
        this.result = result;
    }
}
