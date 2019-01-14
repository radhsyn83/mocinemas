package com.example.fathurradhy.mocinemas.fragment;


import android.widget.Toast;

import com.example.fathurradhy.mocinemas.BuildConfig;
import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.MoviesAdapter;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModel;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;
import com.example.fathurradhy.mocinemas.domain.presenter.MoviesImpls;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NowPlayingFragment extends RecyclerFragment {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sm_layout)
    ShimmerFrameLayout mShimmer;

    @Override
    protected int setLayoutResource() {
        return R.layout.fragment_now_playing;
    }

    @Override
    protected ShimmerFrameLayout setShimmerLayout() {
        return mShimmer;
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mSwipeRefresh;
    }

    @Override
    protected RecyclerView setRecyclerViewLayout() {
        return mRecyclerView;
    }

    @Override
    protected void onSwipeRefresh() {
        loadMovies();
    }

    @Override
    protected void onViewReady() {
        loadMovies();
    }

    private void loadMovies() {
        showLoading(true);

        new MoviesImpls(getContext(), new DefaultView<MoviesModel>() {
            @Override
            public void onSuccess(MoviesModel data) {
                setRecyclerView(data.getResult());
            }

            @Override
            public void onFailed(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }).getNowPlaying(BuildConfig.API_KEY);
    }

    private void setRecyclerView(List<MoviesModelResult> list) {
        showLoading(false);
        MoviesAdapter mMoviesAdapter = new MoviesAdapter(getContext(), list);
        this.mRecyclerView.setAdapter(mMoviesAdapter);
    }

}
