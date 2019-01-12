package com.example.fathurradhy.mocinemas.domain.presenter;

import android.content.Context;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModel;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.example.fathurradhy.mocinemas.net.Config;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesImpls implements MoviesPresenter {

    private Context mContext;
    private DefaultView<MoviesModel> mView;

    public MoviesImpls(Context mContext, DefaultView<MoviesModel> mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void getNowPlaying(String api) {
        Call<MoviesModel> call = Config.getApi().getNowPlaying(api);
        call.enqueue( new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    mView.onSuccess(response.body());
                } else {
                    mView.onFailed(mContext.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                String message = "";

                if (t instanceof SocketTimeoutException) {
                    message = mContext.getResources().getString(R.string.rto);
                } else if (t instanceof ConnectException) {
                    message = mContext.getResources().getString(R.string.no_internet);
                } else {
                    if (t.getMessage() != null)
                        message = t.getMessage();
                }

                mView.onFailed(message);
            }
        });
    }

    @Override
    public void getComingSoon(String api) {
        Call<MoviesModel> call = Config.getApi().getComingSoon(api);
        call.enqueue( new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    mView.onSuccess(response.body());
                } else {
                    mView.onFailed(mContext.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                String message = "";

                if (t instanceof SocketTimeoutException) {
                    message = mContext.getResources().getString(R.string.rto);
                } else if (t instanceof ConnectException) {
                    message = mContext.getResources().getString(R.string.no_internet);
                } else {
                    if (t.getMessage() != null)
                        message = t.getMessage();
                }

                mView.onFailed(message);
            }
        });
    }

    @Override
    public void searchMovies(String api, String query) {
        Call<MoviesModel> call = Config.getApi().getSearch(api, query);
        call.enqueue( new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    mView.onSuccess(response.body());
                } else {
                    mView.onFailed(mContext.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                String message = "";

                if (t instanceof SocketTimeoutException) {
                    message = mContext.getResources().getString(R.string.rto);
                } else if (t instanceof ConnectException) {
                    message = mContext.getResources().getString(R.string.no_internet);
                } else {
                    if (t.getMessage() != null)
                        message = t.getMessage();
                }

                mView.onFailed(message);
            }
        });
    }
}
