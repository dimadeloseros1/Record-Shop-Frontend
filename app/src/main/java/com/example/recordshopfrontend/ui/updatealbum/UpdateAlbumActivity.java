package com.example.recordshopfrontend.ui.updatealbum;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.recordshopfrontend.R;
import com.example.recordshopfrontend.databinding.ActivityAddNewAlbumBinding;
import com.example.recordshopfrontend.databinding.ActivityUpdateAlbumBinding;
import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityUpdateAlbumBinding binding;
    private UpdateAlbumClickHandlers handlers;
    private Albums albums;
    private static final String ALBUMS_KEY = "albums";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_album);

        albums = getIntent().getParcelableExtra(ALBUMS_KEY, Albums.class);
        if (albums == null) {

            finish();
            return;
        }

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_update_album
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handlers = new UpdateAlbumClickHandlers(
                albums,
                this,
                viewModel
        );

        binding.setAlbum(albums);
        binding.setClickHandlers(handlers);
    }
}