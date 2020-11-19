package com.example.moviecatalog;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalog.databinding.FragmentEpisodeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EpisodeFragment extends Fragment {

    FragmentEpisodeBinding fragmentEpisodeBinding;

    List<Movies> movieList;

    float rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View v = inflater.inflate(R.layout.fragment_episode, container, false);

        fragmentEpisodeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_episode, container, false);

        View v = fragmentEpisodeBinding.getRoot();

        final Bundle bundle = this.getArguments();

        fragmentEpisodeBinding.recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        Call<List<Movies>> call = RetroInstance.getRetrofitInstance().getMoviesList();

        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {

                movieList = response.body();

                MovieAdapter movieAdapter = new MovieAdapter(movieList, getContext(), rate);

                fragmentEpisodeBinding.recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {

            }
        });

        return v;

    }
}