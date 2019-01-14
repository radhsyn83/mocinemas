package com.example.fathurradhy.mocinemas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.MoviesColoumns.DATE;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.MoviesColoumns.POSTER;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.MoviesColoumns.RATING;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.MoviesColoumns.TITLE;
import static com.example.fathurradhy.mocinemas.database.DatabaseContract.TABLE_MOVIE;

public class MovieHelper {
    private static String DATABASE_TABLE = TABLE_MOVIE;
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

//    public ArrayList<Movie> query(){
//        ArrayList<Movie> arrayList = new ArrayList<>();
//        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null,_ID +" DESC",null);
//        cursor.moveToFirst();
//        Movie movie;
//        if (cursor.getCount()>0) {
//            do {
//                movie = new Movie();
//                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
//                movie.setRating(cursor.getString(cursor.getColumnIndexOrThrow(RATING)));
//                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
//                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
//
//                arrayList.add(movie);
//                cursor.moveToNext();
//            } while (!cursor.isAfterLast());
//        }
//        cursor.close();
//        return arrayList;
//    }

    public boolean isFavorite(String id) {
        Boolean isFav = false;
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + _ID + "=?", new String[]{id + ""})) {
            isFav = cursor.getCount() > 0;
        } catch (SQLException ignored){}

        return isFav;
    }

//    public long insert(Movie movie){
//        ContentValues initialValues =  new ContentValues();
//        initialValues.put(_ID, movie.getId());
//        initialValues.put(TITLE, movie.getTitle());
//        initialValues.put(RATING, movie.getRating());
//        initialValues.put(DATE, movie.getDate());
//        initialValues.put(POSTER, movie.getPoster());
//        return database.insert(DATABASE_TABLE, null, initialValues);
//    }

//    public int update(Movie movie){
//        ContentValues args = new ContentValues();
//        args.put(TITLE, movie.getTitle());
//        args.put(RATING, movie.getRating());
//        args.put(DATE, movie.getDate());
//        args.put(POSTER, movie.getPoster());
//        return database.update(DATABASE_TABLE, args, _ID + "= '" + movie.getId() + "'", null);
//    }

//    public int delete(int id){
//        return database.delete(TABLE_MOVIE, _ID + " = '"+id+"'", null);
//    }

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
