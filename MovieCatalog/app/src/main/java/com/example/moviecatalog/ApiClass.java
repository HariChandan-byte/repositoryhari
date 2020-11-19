package com.example.moviecatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("shows/11/episodes")
    Call<List<Movies>> getMoviesList();

}
