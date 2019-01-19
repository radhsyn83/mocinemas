package com.example.fathurradhy.mocinemas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;

public abstract class RecyclerFragment extends Fragment {
    Context mContext;
    private RecyclerView mRecyclerView;
    private ShimmerFrameLayout mShimmer;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = null;

        if  (savedInstanceState == null) {
            root = inflater.inflate(setLayoutResource(), container, false);
            ButterKnife.bind(this, root);

            mContext = getActivity();

            if (setShimmerLayout() != null) {
                mShimmer = setShimmerLayout();
            }

            if (setSwipeRefreshLayout() != null) {
                mSwipeRefresh = setSwipeRefreshLayout();
                mSwipeRefresh.setOnRefreshListener(this::onSwipeRefresh);
            }

            if (setRecyclerViewLayout() != null) {
                mRecyclerView = setRecyclerViewLayout();
                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
            }

            onViewReady();
        }

        return root;
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
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mSwipeRefresh.setRefreshing(false);
        }
    }
}
