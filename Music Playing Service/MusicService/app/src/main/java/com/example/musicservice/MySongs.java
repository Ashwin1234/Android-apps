package com.example.musicservice;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class MySongs implements Parcelable {
    String name;
    String artist_name;
    Bitmap image;
    String url;
    protected MySongs(Parcel in) {
        name = in.toString();
        artist_name = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
        url = in.readString();
    }

    public static final Creator<MySongs> CREATOR = new Creator<MySongs>() {
        @Override
        public MySongs createFromParcel(Parcel in) {
            return new MySongs(in);
        }

        @Override
        public MySongs[] newArray(int size) {
            return new MySongs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public MySongs(String name,String artist_name,Bitmap image,String url){
        this.name = name;
        this.artist_name = artist_name;
        this.image = image;
        this.url = url;
    }
}
