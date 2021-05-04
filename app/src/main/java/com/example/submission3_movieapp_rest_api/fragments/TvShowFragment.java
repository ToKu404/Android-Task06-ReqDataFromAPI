package com.example.submission3_movieapp_rest_api.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.activities.DetailTvShowActivity;
import com.example.submission3_movieapp_rest_api.adapters.TvShowAdapter;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvAiringToday;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvAiringTodayResponse;
import com.example.submission3_movieapp_rest_api.networks.Const;
import com.example.submission3_movieapp_rest_api.networks.tvshow.TvApiClient;
import com.example.submission3_movieapp_rest_api.networks.tvshow.TvApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowFragment extends Fragment implements TvShowAdapter.OnItemClick {

    public static final String TAG = "TvShowFragment";
    private RecyclerView recyclerView;
    private TvShowAdapter adapter;
    private List<TvAiringToday> airingTodayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = v.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        return v;
    }

    private void loadData() {
        TvApiInterface tvApiInterface = TvApiClient.getRetrofit().create(TvApiInterface.class);

        final retrofit2.Call<TvAiringTodayResponse> airingTodayCall = tvApiInterface.getAiringToday(Const.API_KEY);
        airingTodayCall.enqueue(new Callback<TvAiringTodayResponse>() {
            @Override
            public void onResponse(Call<TvAiringTodayResponse> call, Response<TvAiringTodayResponse> response) {
                if(response.isSuccessful() && response.body().getAiringTodayList()!=null){
                    airingTodayList = response.body().getAiringTodayList();
                    adapter = new TvShowAdapter(airingTodayList, TvShowFragment.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<TvAiringTodayResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(int pos) {
        Intent detailActivity = new Intent(getActivity(), DetailTvShowActivity.class);
        detailActivity.putExtra("ID", airingTodayList.get(pos).getId());
        detailActivity.putExtra("TITLE", airingTodayList.get(pos).getTitle());
        detailActivity.putExtra("IMG_URL", airingTodayList.get(pos).getImgUrl());
        detailActivity.putExtra("YEAR", airingTodayList.get(pos).getReleaseYear());
        detailActivity.putExtra("OVERVIEW", airingTodayList.get(pos).getOverview());
        detailActivity.putExtra("VOTE", airingTodayList.get(pos).getVoteCount());
        detailActivity.putExtra("BACKDROP", airingTodayList.get(pos).getBackdrop());
        startActivity(detailActivity);
    }
}