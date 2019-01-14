package com.example.fathurradhy.mocinemas.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.FavoritAdapter;
import com.example.fathurradhy.mocinemas.database.Movie;
import com.example.fathurradhy.mocinemas.database.MovieHelper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

import static com.example.fathurradhy.mocinemas.database.DatabaseContract.CONTENT_URI;

public class FavoriteFragment extends RecyclerFragment {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sm_layout)
    ShimmerFrameLayout mShimmer;
    @BindView(R.id.rl_empty)
    RelativeLayout mEmpty;

    private MovieHelper mMovieHelper;
    private Cursor list;

    @Override
    protected int setLayoutResource() {
        return R.layout.fragment_favorite;
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
        new loadFavorites().execute();
    }

    @Override
    protected void onViewReady() {
        mMovieHelper = new MovieHelper(mContext);
        mMovieHelper.open();

        new loadFavorites().execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMovieHelper != null){
            mMovieHelper.close();
        }
    }

    private class loadFavorites extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading(true);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return Objects.requireNonNull(getActivity()).getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor movie) {
            super.onPostExecute(movie);
            showLoading(false);

            list = movie;

            FavoritAdapter adapter = new FavoritAdapter(mContext, list);
            mRecyclerView.setAdapter(adapter);

            if (list.getCount() == 0){
                mShimmer.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                mEmpty.setVisibility(View.VISIBLE);
            } else {
                mShimmer.setVisibility(View.GONE);
                mEmpty.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }

        }
    }
}
