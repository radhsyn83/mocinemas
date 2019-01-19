package com.example.fathurradhy.mocinemas.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final Context mContext;
    private final List<MoviesModelResult> mPopularList;

    public MoviesAdapter(Context mContext, List<MoviesModelResult> mPopularList) {
        this.mContext = mContext;
        this.mPopularList = mPopularList;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int i) {
        final MoviesModelResult movie = mPopularList.get(i);

        Glide.with(mContext).load(BuildConfig.IMG_URL +mPopularList.get(i).getPosterPath()).into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getVoteAverage());

        holder.container.setOnClickListener(v -> {
            Intent i1 = new Intent(mContext, DetailActivity.class);
            i1.putExtra("id", movie.getId());
            i1.putExtra("title", movie.getTitle());

            mContext.startActivity(i1);
        });

    }

    @Override
    public int getItemCount() {
        return mPopularList.size();
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
