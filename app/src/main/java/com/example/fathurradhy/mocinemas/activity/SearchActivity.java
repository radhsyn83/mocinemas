package com.example.fathurradhy.mocinemas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.MoviesAdapter;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModel;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;
import com.example.fathurradhy.mocinemas.domain.presenter.MoviesImpls;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.example.fathurradhy.mocinemas.utils.SupportVariable;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private MoviesAdapter mMoviesAdapter;
    private ShimmerFrameLayout mShimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecycleView = findViewById(R.id.recyclerView);
        mShimmer = findViewById(R.id.sm_layout);

        searchMovie(getIntent().getStringExtra("query"));
    }

    private void searchMovie(String query) {
        mShimmer.startShimmer();
        mShimmer.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);

        new MoviesImpls(this, new DefaultView<MoviesModel>() {
            @Override
            public void onSuccess(MoviesModel data) {
                setRecyclerView(data.getResult());
            }

            @Override
            public void onFailed(String msg) {
                Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();
                Log.e("RETROFIT", msg);
            }
        }).searchMovies(SupportVariable.API_KEY, query);
    }

    private void setRecyclerView(List<MoviesModelResult> list) {

        mShimmer.stopShimmer();
        mShimmer.setVisibility(View.GONE);

        mRecycleView.setVisibility(View.VISIBLE);

        mRecycleView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mMoviesAdapter = new MoviesAdapter(SearchActivity.this, list);
        mRecycleView.setAdapter(mMoviesAdapter);
    }
}
