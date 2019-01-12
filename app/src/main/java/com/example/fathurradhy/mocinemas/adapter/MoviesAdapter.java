package com.example.fathurradhy.mocinemas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.activity.DetailActivity;
import com.example.fathurradhy.mocinemas.domain.model.movies.MoviesModelResult;

import java.util.List;

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popular, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int i) {
        final MoviesModelResult movie = mPopularList.get(i);

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+mPopularList.get(i).getPosterPath()).into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getVoteAverage());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("id", movie.getId());
                i.putExtra("title", movie.getTitle());

                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPopularList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView poster;
        private final TextView title, rating;
        private final LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.iv_poster);
            title = itemView.findViewById(R.id.tv_title);
            rating = itemView.findViewById(R.id.tv_rating);
            container = itemView.findViewById(R.id.ll_container);
        }
    }
}
