package com.example.fathurradhy.mocinemas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fathurradhy.mocinemas.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class FavoriteFragment extends RecyclerFragment {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sm_layout)
    ShimmerFrameLayout mShimmer;

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

    }

    @Override
    protected void onViewReady() {

    }
}
