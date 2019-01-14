
package com.example.fathurradhy.mocinemas.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.fathurradhy.mocinemas.database.DatabaseContract;
import com.example.fathurradhy.mocinemas.database.Movie;
import com.example.fathurradhy.mocinemas.database.MovieHelper;
import com.example.fathurradhy.mocinemas.domain.model.detail.DetailModel;
import com.example.fathurradhy.mocinemas.domain.presenter.DetailImpls;
import com.example.fathurradhy.mocinemas.domain.view.DefaultView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import static android.provider.BaseColumns._ID;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.CONTENT_URI;

public class DetailActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmer;
    private MovieHelper mMovieHelper;
    private DetailModel movieData;
    private MenuItem menuFavorite;
    private Movie movie;

    private Boolean isReady = false;
    private Boolean isFavorite = true;
    private String id;

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

        mMovieHelper = new MovieHelper(this);
        mMovieHelper.open();
    }

    private void loadDetail() {
        new DetailImpls(this, new DefaultView<DetailModel>() {
            @Override
            public void onSuccess(DetailModel data) {
                movieData = data;
                setForm();
            }

            @Override
            public void onFailed(String msg) {

            }
        }).getDetail(id, BuildConfig.API_KEY);
    }

    private void setForm() {
        ((TextView) findViewById(R.id.tv_title)).setText(movieData.getTitle());
        ((TextView) findViewById(R.id.tv_rating)).setText(movieData.getVoteAverage());
        ((TextView) findViewById(R.id.tv_duration)).setText(getResources().getString(R.string.duration, movieData.getRuntime()));
        ((TextView) findViewById(R.id.tv_overview)).setText(movieData.getOverview());

        Glide.with(this)
                .load(BuildConfig.IMG_URL+movieData.getPosterPath())
                .into(((ImageView) findViewById(R.id.iv_background)));

        Glide.with(this)
                .load(BuildConfig.IMG_URL+movieData.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        finish();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mShimmer.setVisibility(View.GONE);
                        mShimmer.stopShimmer();
                        isReady = true;
                        return false;
                    }
                })
                .into(((ImageView) findViewById(R.id.iv_poster)));

        StringBuilder genre = new StringBuilder();

        for (int i = 0; i < movieData.getGenres().size(); i++) {
            if (i == 0)
                genre.append(movieData.getGenres().get(i).getName());
            else
                genre.append(", ").append(movieData.getGenres().get(i).getName());
        }

        ((TextView) findViewById(R.id.tv_genre)).setText(genre.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getFavorite() {
        isFavorite = mMovieHelper.isFavorite(id);
        setFavoriteIcon();
    }

    private void setFavoriteIcon() {
        if (isFavorite)
            menuFavorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_full));
        else
            menuFavorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_empty));
    }

    private void setFavorite() {
        if (isReady) {
            if (isFavorite) {
                getContentResolver().delete(CONTENT_URI, _ID + "=" +id,null);
                getFavorite();
            } else {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

                ContentValues values = new ContentValues();
                values.put(_ID, Integer.parseInt(id));
                values.put(DatabaseContract.MoviesColoumns.TITLE, movieData.getTitle());
                values.put(DatabaseContract.MoviesColoumns.RATING, movieData.getVoteAverage());
                values.put(DatabaseContract.MoviesColoumns.DATE, currentDateTimeString);
                values.put(DatabaseContract.MoviesColoumns.POSTER, movieData.getPosterPath());

                getContentResolver().insert(CONTENT_URI,values);
                getFavorite();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuFavorite = menu.findItem(R.id.action_favorite);
        getFavorite();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite){
            setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMovieHelper != null) {
            mMovieHelper.close();
        }
    }
}
