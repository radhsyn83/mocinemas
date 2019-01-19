package com.example.mofavorite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mofavorite.BuildConfig;
import com.example.mofavorite.R;

import static com.example.mofavorite.db.DatabaseContract.MoviesColoumns.POSTER;
import static com.example.mofavorite.db.DatabaseContract.MoviesColoumns.RATING;
import static com.example.mofavorite.db.DatabaseContract.MoviesColoumns.TITLE;
import static com.example.mofavorite.db.DatabaseContract.getColumnString;

public class FavoritAdapter extends CursorAdapter {
    private Context mContext;

    public FavoritAdapter(Context mContext, Cursor c, boolean autoRequery) {
        super(mContext, c, autoRequery);
        this.mContext = mContext;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            ((TextView)view.findViewById(R.id.tv_title)).setText(getColumnString(cursor,TITLE));
            ((TextView)view.findViewById(R.id.tv_rating)).setText(getColumnString(cursor,RATING));

            Glide.with(mContext)
                    .load(BuildConfig.IMG_URL + getColumnString(cursor, POSTER))
                    .into(((ImageView)view.findViewById(R.id.iv_poster)));
        }
    }
}
