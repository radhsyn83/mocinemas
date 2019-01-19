package com.example.mofavorite.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mofavorite.R;
import com.example.mofavorite.adapter.FavoritAdapter;
import com.example.mofavorite.db.DatabaseContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import static com.example.mofavorite.db.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener{

    private FavoritAdapter mFavoriteAdapter;

    private final int LOAD_MOVIE_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mListView = findViewById(R.id.rv_favorit);
        mFavoriteAdapter = new FavoritAdapter(this, null, true);

        mListView.setAdapter(mFavoriteAdapter);
        mListView.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(LOAD_MOVIE_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_MOVIE_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mFavoriteAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mFavoriteAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        Cursor cursor = (Cursor) mFavoriteAdapter.getItem(position);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.MoviesColoumns._ID));
        Uri uri = Uri.parse(CONTENT_URI+"/"+id);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getResources().getString(R.string.coution));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.are_you_sure))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), (dialog, id1) -> {
                    getContentResolver().delete(uri, null, null);
                    getSupportLoaderManager().restartLoader(LOAD_MOVIE_ID, null, this);
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(getResources().getString(R.string.no), (dialog, id12) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOAD_MOVIE_ID);
    }
}
