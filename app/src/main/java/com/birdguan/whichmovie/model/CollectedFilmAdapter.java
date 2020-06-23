package com.birdguan.whichmovie.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.birdguan.whichmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:14
 */

/**
 * 收藏电影RecycleView的适配器
 */
public class CollectedFilmAdapter extends RecyclerView.Adapter<CollectedFilmAdapter.ViewHolder> {

    private List<CollectedFilm> collectedFilmList;
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
        CollectedFilm collectedFilm = collectedFilmList.get(position);
        Picasso
                .with(holder.itemView.getContext())
                .load(collectedFilm.getImage())
                .placeholder(R.drawable.default_movie_image)
                .into(holder.imageViewCollectedFilm);
        holder.textViewCollectedTitle.setText(collectedFilm.getTitle());
        String year = collectedFilm.getYear();
        holder.textViewCollectedYear.setText(collectedFilm.getYear());
        holder.ratingBarCollectedFilm.setRating(collectedFilm.getRating()/2);
    }

    @Override
    public int getItemCount() {
        return collectedFilmList.size();
    }

    public CollectedFilmAdapter(Context context, List<CollectedFilm> collectedFilmList) {
        this.context = context;
        this.collectedFilmList = collectedFilmList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCollectedFilm;
        TextView textViewCollectedTitle;
        TextView textViewCollectedYear;
        RatingBar ratingBarCollectedFilm;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCollectedFilm = (ImageView) itemView.findViewById(R.id.iv_watched_film);
            textViewCollectedTitle = (TextView) itemView.findViewById(R.id.tv_watched_film_title);
            textViewCollectedYear = (TextView) itemView.findViewById(R.id.tv_watched_film_year);
            ratingBarCollectedFilm = (RatingBar) itemView.findViewById(R.id.rb_watched_film);
        }
    }
}
