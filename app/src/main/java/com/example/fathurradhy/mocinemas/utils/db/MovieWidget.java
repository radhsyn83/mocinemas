package com.example.fathurradhy.mocinemas.utils.db;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieWidget implements Parcelable {
    private int id;
    private String title;
    private String rating;
    private String date;
    private String poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.rating);
        dest.writeString(this.date);
        dest.writeString(this.poster);
    }

    public MovieWidget(){
    }

    private MovieWidget(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.rating = in.readString();
        this.date = in.readString();
        this.poster = in.readString();
    }

    public static final Creator<MovieWidget> CREATOR = new Creator<MovieWidget>() {
        @Override
        public MovieWidget createFromParcel(Parcel source) {
            return new MovieWidget(source);
        }

        @Override
        public MovieWidget[] newArray(int size) {
            return new MovieWidget[size];
        }
    };
}
