package com.shore.tuttopazzo.movierandom.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.shore.tuttopazzo.movierandom.data.MovieDataContract.MovieEntry;

/**
 * Created by TuttoPazzo$$ on 09-Oct-16.
 */
public class MovieDBHellper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movie.db";

    public MovieDBHellper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.i("charan","onCreate DB");

        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID + " INTEGER PRIMARY KEY," +
                MovieEntry.COLUMN_MOVIE + " TEXT UNIQUE NOT NULL, " +
                MovieEntry.COLUMN_GENRE + " TEXT NOT NULL " +

                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);


      //  insertDefaultValues(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void insertDefaultValues(SQLiteDatabase sqLiteDatabase){
        ContentValues testValues = new ContentValues();
        testValues.put(MovieEntry.COLUMN_MOVIE, "Inception");
        testValues.put(MovieEntry.COLUMN_GENRE, "action");


        Log.i("charan","inserting default values");

        sqLiteDatabase.insert(MovieEntry.TABLE_NAME,null,testValues);

        testValues.put(MovieEntry.COLUMN_MOVIE, "Wall-E");
        testValues.put(MovieEntry.COLUMN_GENRE, "anime");

        sqLiteDatabase.insert(MovieEntry.TABLE_NAME,null,testValues);

        Log.i("charan","inserted default values");

       /* Cursor c = sqLiteDatabase.rawQuery("SELECT movie  FROM  movies", null);

        c.moveToFirst();

        int columnNameIndex = c.getColumnIndex("movie");

        String movie =c.getString(columnNameIndex);

        Log.i("charan", "getting inserted values"+movie);*/



    }


}
