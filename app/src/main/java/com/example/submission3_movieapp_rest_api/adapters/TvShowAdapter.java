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
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowAiringToday;
import com.example.submission3_movieapp_rest_api.networks.Const;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private List<TvShowAiringToday> tvShowAiringTodayList;
    private OnItemClick onItemClick;

    public TvShowAdapter(List<TvShowAiringToday> tvShowAiringTodayList, OnItemClick onItemClick){
        this.tvShowAiringTodayList = tvShowAiringTodayList;
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
        Glide.with(holder.itemView.getContext()).load(Const.IMG_URL_200 + tvShowAiringTodayList.get(position).getImgUrl()).into(holder.ivPoster);
        holder.tvTitle.setText(tvShowAiringTodayList.get(position).getTitle());
        holder.tvReleaseYear.setText(tvShowAiringTodayList.get(position).getReleaseYear());
        holder.tvVote.setText(tvShowAiringTodayList.get(position).getVoteCount());
        holder.tvSinopsis.setText(tvShowAiringTodayList.get(position).getOverview());

    }

    @Override
    public int getItemCount() {
        return tvShowAiringTodayList.size();
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
