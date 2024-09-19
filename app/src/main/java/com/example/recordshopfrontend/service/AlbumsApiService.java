package com.example.recordshopfrontend.service;

import com.example.recordshopfrontend.model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumsApiService {

    @GET("albums")
    Call<List<Albums>> getAllAlbums();

    @POST("albums")
    Call<Albums> postAlbums(@Body Albums albums);
}
