package com.example.recordshopfrontend.service;

import com.example.recordshopfrontend.model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumsApiService {

    @GET("albums")
    Call<List<Albums>> getAllAlbums();

    @POST("albums")
    Call<Albums> postAlbums(@Body Albums albums);

    @PUT("albums/{id}")
    Call<Albums> uptadeAlbums(@Path("id") long id, @Body Albums albums);

    @DELETE("albums/{id}")
    Call<Void> deleteAlbums(@Path("id") long id);

}
