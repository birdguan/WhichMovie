package com.birdguan.whichmovie.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.birdguan.whichmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:14
 */

/**
 * 已观看电影RecycleView的Adapter
 */
public class WatchedFilmAdapter extends RecyclerView.Adapter<WatchedFilmAdapter.ViewHolder> {

    private List<WatchedFilm> watchedFilmList;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WatchedFilm watchedFilm = watchedFilmList.get(position);
        Picasso
                .with(holder.itemView.getContext())
                .load(watchedFilm.getImage())
                .placeholder(R.drawable.default_movie_image)
                .into(holder.filmImage);
        holder.textViewTitle.setText(watchedFilm.getTitle());
        holder.textViewYear.setText(watchedFilm.getYear());
        holder.ratingBarFilm.setRating(watchedFilm.getRating()/2);
    }

    @Override
    public int getItemCount() {
        return watchedFilmList.size();
    }

    public WatchedFilmAdapter(Context context, List<WatchedFilm> watchedFilmList) {
        this.context = context;
        this.watchedFilmList = watchedFilmList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView filmImage;
        TextView textViewTitle;
        TextView textViewYear;
        RatingBar ratingBarFilm;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filmImage = (ImageView) itemView.findViewById(R.id.iv_watched_film);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_watched_film_title);
            textViewYear = (TextView) itemView.findViewById(R.id.tv_watched_film_year);
            ratingBarFilm = (RatingBar) itemView.findViewById(R.id.rb_watched_film);
        }
    }
}
