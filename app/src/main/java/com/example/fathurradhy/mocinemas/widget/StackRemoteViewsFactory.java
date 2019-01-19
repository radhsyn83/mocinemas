package com.example.fathurradhy.mocinemas.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.fathurradhy.mocinemas.BuildConfig;
import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.utils.db.Movie;

import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.CONTENT_URI;

public class StackRemoteViewsFactory implements
        RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private Cursor cursor;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {
        if (cursor != null) {
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        cursor = mContext.getContentResolver().query(CONTENT_URI, null, null, null, null);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                cursor == null || !cursor.moveToPosition(position)) {
            return null;
        }
        Movie movie = getItem(position);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        try {
            String url = BuildConfig.IMG_URL+movie.getPoster();
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(url)
                    .submit(512, 1024)
                    .get();

            rv.setImageViewBitmap(R.id.imageView, bitmap);
            rv.setTextViewText(R.id.tv_title, movie.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle extras = new Bundle();
        extras.putInt(ImagesBannerWidget.EXTRA_MOVIE_ID, movie.getId());
        extras.putString(ImagesBannerWidget.EXTRA_MOVIE_JUDUL, movie.getTitle());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private Movie getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }

        return new Movie(cursor);
    }
}
