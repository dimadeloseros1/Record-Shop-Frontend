package com.example.recordshopfrontend.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recordshopfrontend.R;
import com.example.recordshopfrontend.databinding.AlbumLayoutBinding;
import com.example.recordshopfrontend.model.Albums;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    List<Albums> albumsList;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public AlbumAdapter(Context context, List<Albums> albums, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.albumsList = albums;
        this.recyclerViewInterface = recyclerViewInterface;
    }



    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumLayoutBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.album_layout,
                        parent,
                        false);

        return new AlbumViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Albums albums = albumsList.get(position);
        holder.albumItemBinding.setAlbums(albums);
    }



    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        private AlbumLayoutBinding albumItemBinding;

        public AlbumViewHolder(@NonNull AlbumLayoutBinding albumItemBinding, RecyclerViewInterface recyclerViewInterface) {
            super(albumItemBinding.getRoot());
            this.albumItemBinding = albumItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
