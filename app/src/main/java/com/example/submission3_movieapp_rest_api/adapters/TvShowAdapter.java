package com.example.submission3_movieapp_rest_api.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvAiringToday;
import com.example.submission3_movieapp_rest_api.networks.Const;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private List<TvAiringToday> tvAiringTodayList;
    private OnItemClick onItemClick;

    public TvShowAdapter(List<TvAiringToday> tvAiringTodayList, OnItemClick onItemClick){
        this.tvAiringTodayList = tvAiringTodayList;
        this.onItemClick = onItemClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_recycler_item, parent, false);
        return new ViewHolder(v, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(Const.IMG_URL_200 + tvAiringTodayList.get(position).getImgUrl()).into(holder.ivPoster);
        holder.tvTitle.setText(tvAiringTodayList.get(position).getTitle());
        holder.tvReleaseYear.setText(tvAiringTodayList.get(position).getReleaseYear());
        holder.tvVote.setText(tvAiringTodayList.get(position).getVoteCount());
        holder.tvSinopsis.setText(tvAiringTodayList.get(position).getOverview());

    }

    @Override
    public int getItemCount() {
        return tvAiringTodayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvReleaseYear;
        TextView tvVote;
        TextView tvSinopsis;

        public ViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_tvshow_image);
            tvTitle = itemView.findViewById(R.id.tv_tvshow_name);
            tvReleaseYear = itemView.findViewById(R.id.tv_tvshow_year);
            tvVote = itemView.findViewById(R.id.tv_tvshow_vote);
            tvSinopsis = itemView.findViewById(R.id.tv_tvshow_sinopsis);
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
