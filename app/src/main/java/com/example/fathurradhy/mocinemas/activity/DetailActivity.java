
package com.example.fathurradhy.mocinemas.activity;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.fathurradhy.mocinemas.BuildConfig;
import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.domain.model.detail.DetailModel;
import com.example.fathurradhy.mocinemas.domain.presenter.DetailImpls;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private String id;
    private ShimmerFrameLayout mShimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();

        id = getIntent().getStringExtra("id");

        loadDetail();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getIntent().getStringExtra("title"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mShimmer = findViewById(R.id.sm_layout);
        mShimmer.startShimmer();
    }

    private void loadDetail() {
        new DetailImpls(this, new DefaultView<DetailModel>() {
            @Override
            public void onSuccess(DetailModel data) {
                setForm(data);
            }

            @Override
            public void onFailed(String msg) {

            }
        }).getDetail(id, BuildConfig.API_KEY);
    }

    private void setForm(DetailModel data) {
        ((TextView) findViewById(R.id.tv_title)).setText(data.getTitle());
        ((TextView) findViewById(R.id.tv_rating)).setText(data.getVoteAverage());
        ((TextView) findViewById(R.id.tv_duration)).setText(getResources().getString(R.string.duration, data.getRuntime()));
        ((TextView) findViewById(R.id.tv_overview)).setText(data.getOverview());

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+data.getPosterPath())
                .into(((ImageView) findViewById(R.id.iv_background)));

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+data.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        finish();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mShimmer.setVisibility(View.GONE);
                        mShimmer.stopShimmer();
                        return false;
                    }
                })
                .into(((ImageView) findViewById(R.id.iv_poster)));

        StringBuilder genre = new StringBuilder();

        for (int i = 0; i < data.getGenres().size(); i++) {
            if (i == 0)
                genre.append(data.getGenres().get(i).getName());
            else
                genre.append(", ").append(data.getGenres().get(i).getName());
        }

        ((TextView) findViewById(R.id.tv_genre)).setText(genre.toString());


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
