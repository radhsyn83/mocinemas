package com.example.fathurradhy.mocinemas.domain.presenter;

import android.content.Context;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.domain.model.detail.DetailModel;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.example.fathurradhy.mocinemas.net.Config;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailImpls implements DetailPresenter {

    private Context mContext;
    private DefaultView<DetailModel> mView;

    public DetailImpls(Context mContext, DefaultView<DetailModel> mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void getDetail(String id, String api) {
        Call<DetailModel> call = Config.getApi().getDetail(id, api);
        call.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailModel> call, @NonNull Response<DetailModel> response) {
                if (response.isSuccessful()) {
                    mView.onSuccess(response.body());
                } else {
                    mView.onFailed(mContext.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NonNull Call<DetailModel> call, Throwable t) {
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
