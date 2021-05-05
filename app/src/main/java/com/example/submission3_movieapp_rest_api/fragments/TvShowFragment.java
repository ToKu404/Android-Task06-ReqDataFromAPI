package com.example.submission3_movieapp_rest_api.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.activities.DetailTvShowActivity;
import com.example.submission3_movieapp_rest_api.adapters.TvShowAdapter;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowAiringToday;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowAiringTodayResponse;
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
    private List<TvShowAiringToday> airingTodayList;
    private ProgressBar tvProgressBar;
    private TextView tvNoRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = v.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvProgressBar = v.findViewById(R.id.pb_tvshow);
        tvNoRecord = v.findViewById(R.id.tv_ts_empty);
        loadData();
        return v;
    }

    private void loadData() {
        TvApiInterface tvApiInterface = TvApiClient.getRetrofit().create(TvApiInterface.class);

        final retrofit2.Call<TvShowAiringTodayResponse> airingTodayCall = tvApiInterface.getAiringToday(Const.API_KEY);
        airingTodayCall.enqueue(new Callback<TvShowAiringTodayResponse>() {
            @Override
            public void onResponse(Call<TvShowAiringTodayResponse> call, Response<TvShowAiringTodayResponse> response) {
                if(response.isSuccessful() && response.body().getAiringTodayList()!=null){
                    airingTodayList = response.body().getAiringTodayList();
                    adapter = new TvShowAdapter(airingTodayList, TvShowFragment.this);
                    recyclerView.setAdapter(adapter);
                    tvProgressBar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                    tvNoRecord.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<TvShowAiringTodayResponse> call, Throwable t) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        tvProgressBar.setVisibility(View.GONE);
                        tvNoRecord.setVisibility(View.VISIBLE);
                    }
                }, 3000);
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
        startActivity(detailActivity);
    }
}