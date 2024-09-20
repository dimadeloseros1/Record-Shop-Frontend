package com.example.recordshopfrontend.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.recordshopfrontend.ui.addalbum.AddNewAlbumActivity;

public class MainActivityClickHandler {

    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void addNewAlbumBtn(View view) {

        Intent intent = new Intent(view.getContext(), AddNewAlbumActivity.class);

        context.startActivity(intent);
    }
}
