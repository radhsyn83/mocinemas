package com.example.fathurradhy.mocinemas.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.MoviesColoumns.DATE;
import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.MoviesColoumns.POSTER;
import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.MoviesColoumns.RATING;
import static com.example.fathurradhy.mocinemas.utils.db.DatabaseContract.MoviesColoumns.TITLE;

public class MovieHelper {
    private static String DATABASE_TABLE = DatabaseContract.TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public boolean isFavorite(String id) {
        boolean isFav = false;
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + _ID + "=?", new String[]{id + ""})) {
            isFav = cursor.getCount() > 0;
        } catch (SQLException ignored){}

        return isFav;
    }

    public ArrayList<MovieWidget> query(){
        ArrayList<MovieWidget> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        MovieWidget movie;
        if (cursor.getCount()>0) {
            do {

                movie = new MovieWidget();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }

}
