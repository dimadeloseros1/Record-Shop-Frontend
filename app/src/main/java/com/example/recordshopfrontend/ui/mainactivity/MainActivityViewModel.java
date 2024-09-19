package com.example.recordshopfrontend.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.repository.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.albumRepository = new AlbumRepository(application);
    }

    public LiveData<List<Albums>> getAllAlbums() {
        return albumRepository.getMutableLiveData();
    }

    public void postAlbum(Albums albums) {
        albumRepository.addNewAlbum(albums);
    }
}
