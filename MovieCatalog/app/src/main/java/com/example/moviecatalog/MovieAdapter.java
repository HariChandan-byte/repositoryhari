package com.example.moviecatalog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecatalog.databinding.MovieRowBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Vh> {

    MovieRowBinding movieRowBinding;

    List<Movies> moviesList;

    Context context;

    List<Integer> movies = new ArrayList<>();

    float rate;

    static HashMap<Integer, Integer> mapItems = new HashMap<>();

    CountSelectedItems countSelectedItems;

    int c;

    public MovieAdapter(List<Movies> moviesList, Context context, float rate) {

        this.moviesList = moviesList;
        this.context = context;
        this.rate = rate;

    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        movieRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_row, parent, false);

        return new Vh(movieRowBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final Vh holder, final int position) {

        holder.movieRowBinding.textView.setText(moviesList.get(position).getName());
        holder.movieRowBinding.textView2.setText("Season: " + moviesList.get(position).getSeason().toString());
        holder.movieRowBinding.textView3.setText("Episode: " + moviesList.get(position).getNumber().toString());
        holder.movieRowBinding.textView4.setText(moviesList.get(position).getRuntime().toString() + "Minutes");

        Glide.with(context).load(moviesList.get(position).getImage().getMedium()).into(holder.movieRowBinding.imageView2);

        holder.movieRowBinding.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                bundle.putString("Name", moviesList.get(position).getName());
                bundle.putInt("Season", moviesList.get(position).getSeason());
                bundle.putInt("Number", moviesList.get(position).getNumber());
                bundle.putInt("RunTime", moviesList.get(position).getRuntime());
                bundle.putString("Summary", moviesList.get(position).getSummary());
                bundle.putString("ImageUrl", moviesList.get(position).getImage().getOriginal());

                DescriptionFragment descriptionFragment = new DescriptionFragment();

                descriptionFragment.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, descriptionFragment).commit();

                c = holder.getAdapterPosition();

                addToItemList(c);

                notifyItemChanged(c);

            }
        });

    }

    private void addToItemList(int c) {

        movies.add(c);

        for(int i = 0; i < movies.size(); i++) {

            int occ = Collections.frequency(movies, movies.get(i));

            mapItems.put(movies.get(i), occ);

        }

        Log.i("HashMapInfo", ""+mapItems);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class Vh extends RecyclerView.ViewHolder {

        MovieRowBinding movieRowBinding;

        public Vh(@NonNull MovieRowBinding itemView) {
            super(itemView.getRoot());

            movieRowBinding = itemView;

        }
    }
}