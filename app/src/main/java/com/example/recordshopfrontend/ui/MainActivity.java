package com.example.recordshopfrontend.ui;

import static android.provider.MediaStore.Audio.AudioColumns.ALBUM_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

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
    private SearchView searchView;
//    private SearchView categorySearchView;
    private ArrayList<Albums> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );


        // Search view by artist name
        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        getAllAlbums();

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                filterList(s);

                return true;
            }
        });

//        categorySearchView = findViewById(R.id.categorySearchView);
//        categorySearchView.clearFocus();
//
//        categorySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String text) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String text) {
//
//                filterList(text);
//
//                return true;
//            }
//        });
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

        if (filteredList == null || filteredList.isEmpty()) {
            intent.putExtra(ALBUMS_KEY, albumsList.get(position));

        } else {
            intent.putExtra(ALBUMS_KEY, filteredList.get(position));
        }

        startActivity(intent);
    }

    private void filterList(String text) {

        filteredList = new ArrayList<>();

        for (Albums albums : albumsList) {

            if (albums.getArtist().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(albums);
            }

//            if (albums.getGenre().toUpperCase().contains(text.toUpperCase())) {
//                filteredList.add(albums);
//            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No album found", Toast.LENGTH_SHORT).show();
        } else {
            albumAdapter.setFilteredList(filteredList);
        }
    }

//    private void categoryFilteredList(String text) {
//
//        categorySearchView
//    }
}