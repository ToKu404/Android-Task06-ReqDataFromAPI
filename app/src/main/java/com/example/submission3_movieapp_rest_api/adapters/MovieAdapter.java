package com.example.submission3_movieapp_rest_api.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.movie.MovieNowPlaying;
import com.example.submission3_movieapp_rest_api.networks.Const;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.GridViewHolder> {
    private List<MovieNowPlaying> movieNowPlayingList;
    private OnItemClick onItemClick;

    public MovieAdapter(List<MovieNowPlaying> movieNowPlayingList, OnItemClick onItemClick){
        this.movieNowPlayingList = movieNowPlayingList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item, parent, false);
        return new GridViewHolder(v, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(Const.IMG_URL_200 + movieNowPlayingList.get(position).getImgUrl()).into(holder.ivPoster);
        holder.tvTitle.setText(movieNowPlayingList.get(position).getTitle());
        holder.tvReleaseYear.setText(movieNowPlayingList.get(position).getReleaseDate());
        holder.tvVoteAverage.setText(movieNowPlayingList.get(position).getVoteAverage());
        holder.ratingBar.setRating(movieNowPlayingList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return movieNowPlayingList.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvReleaseYear;
        TextView tvVoteAverage;
        RatingBar ratingBar;

        public GridViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_movie_poster);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseYear = itemView.findViewById(R.id.tv_movie_release_year);
            tvVoteAverage = itemView.findViewById(R.id.tv_movie_vote);
            ratingBar = itemView.findViewById(R.id.rating_movie);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getAdapterPosition());

        }
    }
    public interface OnItemClick {
        void onClick(int pos);
    }
}
