package com.example.fathurradhy.mocinemas.utils.db;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;

public class Movie implements Parcelable {
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

    public Movie(Cursor cursor){
        this.id = DatabaseContract.getColumnInt(cursor, _ID);
        this.title = DatabaseContract.getColumnString(cursor, DatabaseContract.MoviesColoumns.TITLE);
        this.rating = DatabaseContract.getColumnString(cursor, DatabaseContract.MoviesColoumns.RATING);
        this.date = DatabaseContract.getColumnString(cursor, DatabaseContract.MoviesColoumns.DATE);
        this.poster = DatabaseContract.getColumnString(cursor, DatabaseContract.MoviesColoumns.POSTER);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.rating = in.readString();
        this.date = in.readString();
        this.poster = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
