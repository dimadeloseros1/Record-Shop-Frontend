package com.example.recordshopfrontend.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.service.AlbumsApiService;
import com.example.recordshopfrontend.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {

    private MutableLiveData<List<Albums>> mutableLiveData = new MutableLiveData<>();
    private Application application = new Application();

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData getMutableLiveData() {

        AlbumsApiService albumsApiService = RetrofitInstance.getService();
        Call<List<Albums>> call = albumsApiService.getAllAlbums();

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                List<Albums> albumsList = response.body();
                mutableLiveData.setValue(albumsList);
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Log.i("HTTP failure", t.getMessage());
            }
        });


        return mutableLiveData;
    }

    public void addNewAlbum(Albums albums) {
        AlbumsApiService albumsApiService = RetrofitInstance.getService();
        Call<Albums> call = albumsApiService.postAlbums(albums);

        call.enqueue(new Callback<Albums>() {
            @Override
            public void onResponse(Call<Albums> call, Response<Albums> response) {
                Toast.makeText(application.getApplicationContext(), "Album added to the database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Albums> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Fail! Album could not be added to the database", Toast.LENGTH_SHORT).show();
                Log.e("POST REQ", t.getMessage());
            }
        });
    }
}
