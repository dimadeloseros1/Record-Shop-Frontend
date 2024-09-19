package com.example.recordshopfrontend.ui;

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
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Albums> albumsList;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);


            getAllAlbums();
    }

    private void getAllAlbums() {
        mainActivityViewModel.getAllAlbums().observe(this, new Observer<List<Albums>>() {
            @Override
            public void onChanged(List<Albums> albumsFromLiveData) {
                albumsList = (ArrayList<Albums>) albumsFromLiveData;
                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView() {

        recyclerView = activityMainBinding.recyclerView;
        albumAdapter = new AlbumAdapter(this, albumsList);
        recyclerView.setAdapter(albumAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        albumAdapter.notifyDataSetChanged();
    }
}