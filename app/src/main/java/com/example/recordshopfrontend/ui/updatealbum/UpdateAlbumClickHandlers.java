package com.example.recordshopfrontend.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.recordshopfrontend.model.Albums;
import com.example.recordshopfrontend.ui.MainActivity;
import com.example.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

import java.util.Objects;

public class UpdateAlbumClickHandlers {

    private Albums albums;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;
    private long albumId;

    public UpdateAlbumClickHandlers(Albums albums, Context context, MainActivityViewModel mainActivityViewModel) {
        this.albums = albums;
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onSubmitBtnClicked(View view) {

        Albums updateAlbums = new Albums(
                albums.getId(),
                albums.getStock(),
                albums.getArtist(),
                albums.getReleaseYear(),
                albums.getGenre(),
                albums.getAlbumName()
        );

        if (Objects.equals(updateAlbums.getArtist(), "") ||
                Objects.equals(updateAlbums.getAlbumName(), "") ||
                Objects.equals(updateAlbums.getGenre(), "")
        ) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);

            albumId = albums.getId();

            mainActivityViewModel.updateAlbum(albumId, updateAlbums);

            context.startActivity(intent);
        }
    }

    public void onDeleteBtnClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);

        albumId = albums.getId();

        mainActivityViewModel.deleteAlbum(albumId);

        context.startActivity(intent);
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }
}
