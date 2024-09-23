package com.example.recordshopfrontend.ui;

import static android.provider.MediaStore.Audio.AudioColumns.ALBUM_KEY;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recordshopfrontend.R;
import com.example.recordshopfrontend.databinding.ActivityMainBinding;
import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.ui.mainactivity.AlbumAdapter;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityClickHandler;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;
import com.example.recordshopfrontend.ui.mainactivity.RecyclerViewInterface;
import com.example.recordshopfrontend.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<Albums> albumsList;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private MainActivityClickHandler clickHandler;
    private static final String ALBUMS_KEY = "albums";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        getAllAlbums();
    }

    private void getAllAlbums() {
        mainActivityViewModel.getAllAlbums().observe(this, new Observer<List<Albums>>() {
            @Override
            public void onChanged(List<Albums> albumsFromLiveData) {
                albumsList = (ArrayList<Albums>) albumsFromLiveData;
                displayAlbumInRecyclerView();
            }
        });
    }

    private void displayAlbumInRecyclerView() {

        recyclerView = binding.recyclerView;
        albumAdapter = new AlbumAdapter(this, albumsList, this);
        recyclerView.setAdapter(albumAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        albumAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);

        intent.putExtra(ALBUMS_KEY, albumsList.get(position));
        startActivity(intent);

    }
}