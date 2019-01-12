package com.example.fathurradhy.mocinemas.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.MoviesAdapter;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModel;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;
import com.example.fathurradhy.mocinemas.domain.presenter.MoviesImpls;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.example.fathurradhy.mocinemas.utils.SupportVariable;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class ComingSoonActivity extends Fragment {


    private MaterialSearchView mSearchView;
    private RecyclerView mRecycleView;
    private MoviesAdapter mMoviesAdapter;
    private ShimmerFrameLayout mShimmer;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadPopular();

    }

    private void init() {
        mRecycleView = root.findViewById(R.id.recyclerView);
        mShimmer = root.findViewById(R.id.sm_layout);
    }

    private void loadPopular() {
        mShimmer.startShimmer();
        mShimmer.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);

        new MoviesImpls(getContext(), new DefaultView<MoviesModel>() {
            @Override
            public void onSuccess(MoviesModel data) {
                setRecyclerView(data.getResult());
            }

            @Override
            public void onFailed(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                Log.e("RETROFIT", msg);
            }
        }).getComingSoon(SupportVariable.API_KEY);
    }

    private void setRecyclerView(List<MoviesModelResult> list) {

        mShimmer.stopShimmer();
        mShimmer.setVisibility(View.GONE);

        mRecycleView.setVisibility(View.VISIBLE);

        mRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        mMoviesAdapter = new MoviesAdapter(getContext(), list);
        mRecycleView.setAdapter(mMoviesAdapter);
    }

}
