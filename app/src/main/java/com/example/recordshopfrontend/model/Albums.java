package com.example.recordshopfrontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.recordshopfrontend.BR;
import com.google.gson.annotations.SerializedName;


public class Albums extends BaseObservable implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("stock")
    private int stock;

    @SerializedName("artist")
    private String artist;

    @SerializedName("releaseYear")
    private int releaseYear;

    @SerializedName("genre")
    private String genre;

    @SerializedName("albumName")
    private String albumName;

    public Albums(Long id, int stock, String artist, int releaseYear, String genre, String albumName) {
        this.id = id;
        this.stock = stock;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.albumName = albumName;
    }

    public Albums() {}

    protected Albums(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        stock = in.readInt();
        artist = in.readString();
        releaseYear = in.readInt();
        genre = in.readString();
        albumName = in.readString();
    }

    public static final Creator<Albums> CREATOR = new Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel in) {
            return new Albums(in);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };

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
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeInt(stock);
        parcel.writeString(artist);
        parcel.writeInt(releaseYear);
        parcel.writeString(genre);
        parcel.writeString(albumName);
    }
}
