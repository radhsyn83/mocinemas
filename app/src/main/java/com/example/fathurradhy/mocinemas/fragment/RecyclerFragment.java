package com.example.fathurradhy.mocinemas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;

public abstract class RecyclerFragment extends Fragment {
    Context mContext;
    RecyclerView mRecycleView;
    ShimmerFrameLayout mShimmer;
    SwipeRefreshLayout mSwipeRefresh;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(setLayoutResource(), container, false);
        ButterKnife.bind(this, root);

        mContext = getActivity();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (setShimmerLayout() != null) {
            mShimmer = setShimmerLayout();
        }

        if (setSwipeRefreshLayout() != null) {
            mSwipeRefresh = setSwipeRefreshLayout();
            mSwipeRefresh.setOnRefreshListener(() -> onSwipeRefresh());
        }

        if (setRecyclerViewLayout() != null) {
            mRecycleView = setRecyclerViewLayout();
            mRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        }

        onViewReady();
    }

    protected abstract int setLayoutResource();
    protected abstract ShimmerFrameLayout setShimmerLayout();
    protected abstract SwipeRefreshLayout setSwipeRefreshLayout();
    protected abstract RecyclerView setRecyclerViewLayout();

    protected abstract void onSwipeRefresh();
    protected abstract void onViewReady();

    void showLoading(Boolean isShow) {
        if (isShow) {
            mShimmer.startShimmer();
            mShimmer.setVisibility(View.VISIBLE);
            mRecycleView.setVisibility(View.GONE);
        } else {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);
            mSwipeRefresh.setRefreshing(false);
        }
    }
}
