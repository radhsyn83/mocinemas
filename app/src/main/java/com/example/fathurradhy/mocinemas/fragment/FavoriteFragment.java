package com.example.fathurradhy.mocinemas.fragment;

import android.database.Cursor;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.FavoritAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.CONTENT_URI;

public class FavoriteFragment extends RecyclerFragment {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sm_layout)
    ShimmerFrameLayout mShimmer;
    @BindView(R.id.rl_empty)
    RelativeLayout mEmpty;

    private Cursor cursor;

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
        loadFavorite();
    }

    @Override
    protected void onViewReady() {
        loadFavorite();
    }

    private void loadFavorite() {
        if (cursor != null) {
            cursor.close();
        }

        showLoading(true);
        cursor = mContext.getContentResolver().query(CONTENT_URI, null, null, null, null);
        FavoritAdapter adapter = new FavoritAdapter(mContext, cursor);
        mRecyclerView.setAdapter(adapter);

        if (cursor.getCount() == 0){
            showLoading(false);
            mShimmer.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            showLoading(false);
            mShimmer.setVisibility(View.GONE);
            mEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
