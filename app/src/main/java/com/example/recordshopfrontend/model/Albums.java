package com.example.recordshopfrontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.recordshopfrontend.BR;
import com.google.gson.annotations.SerializedName;


public class Albums extends BaseObservable {

    @SerializedName("id")
    private Long id;

    @SerializedName("stock")
    private int stock;

    @SerializedName("artist")
    private String artist;

    @SerializedName("releaseYear")
    private int releaseYear;

    @SerializedName("genre")
    private Genre genre;

    @SerializedName("albumName")
    private String albumName;

    public Albums(Long id, int stock, String artist, int releaseYear, Genre genre, String albumName) {
        this.id = id;
        this.stock = stock;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.albumName = albumName;
    }

    public Albums() {}

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
        notifyPropertyChanged(BR.albumName);
    }
}
