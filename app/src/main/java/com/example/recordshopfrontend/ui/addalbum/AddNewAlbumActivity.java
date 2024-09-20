package com.example.recordshopfrontend.ui.addalbum;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.recordshopfrontend.R;
import com.example.recordshopfrontend.databinding.ActivityAddNewAlbumBinding;
import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

public class AddNewAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private Albums albums;
    private AddAlbumClickHandlers clickHandlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_album);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        albums = new Albums();

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_add_new_album
        );

        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);



        clickHandlers = new AddAlbumClickHandlers(albums, this, mainActivityViewModel);

        binding.setClickHandler(clickHandlers);

        binding.setAlbum(albums);


    }


}