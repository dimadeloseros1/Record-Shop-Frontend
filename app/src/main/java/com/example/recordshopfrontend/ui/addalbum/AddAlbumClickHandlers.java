package com.example.recordshopfrontend.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.ui.MainActivity;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

     Albums albums;
     Context context;
     MainActivityViewModel mainActivityViewModel;

    public AddAlbumClickHandlers(Albums albums, Context context, MainActivityViewModel mainActivityViewModel) {
        this.albums = albums;
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onSubmitClickHandler(View view) {

        if (albums.getArtist() == null || albums.getAlbumName() == null  || albums.getReleaseYear() <= 1900 || albums.getStock() <= 0) {
            Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(context, MainActivity.class);

            Albums newAlbums = new Albums(
                    albums.getId(),
                    albums.getStock(),
                    albums.getArtist(),
                    albums.getReleaseYear(),
                    albums.getGenre(),
                    albums.getAlbumName()
            );

            mainActivityViewModel.postAlbum(newAlbums);

            context.startActivity(intent);
        }
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }
}
