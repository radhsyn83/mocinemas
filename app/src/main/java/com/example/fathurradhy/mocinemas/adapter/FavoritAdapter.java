package com.example.fathurradhy.mocinemas.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fathurradhy.mocinemas.BuildConfig;
import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.activity.DetailActivity;
import com.example.fathurradhy.mocinemas.database.Movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {
    private final Context mContext;
    private final Cursor movie;

    public FavoritAdapter(Context mContext, Cursor movie) {
        this.mContext = mContext;
        this.movie = movie;
    }

    @NonNull
    @Override
    public FavoritAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popular, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritAdapter.ViewHolder holder, int i) {
        final Movie movie = getItem(i);

        Glide.with(mContext).load(BuildConfig.IMG_URL + movie.getPoster()).into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getRating());

        holder.container.setOnClickListener(v -> {
            Intent i1 = new Intent(mContext, DetailActivity.class);
            i1.putExtra("id", movie.getId()+"");
            i1.putExtra("title", movie.getTitle());

            mContext.startActivity(i1);
        });

    }

    private Movie getItem(int position){
        if (!movie.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Movie(movie);
    }
    @Override
    public int getItemCount() {
        if (movie == null) return 0;
        return movie.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView poster;
        private final TextView title, rating;
        private final LinearLayout container;

        ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.iv_poster);
            title = itemView.findViewById(R.id.tv_title);
            rating = itemView.findViewById(R.id.tv_rating);
            container = itemView.findViewById(R.id.ll_container);
        }
    }
}
