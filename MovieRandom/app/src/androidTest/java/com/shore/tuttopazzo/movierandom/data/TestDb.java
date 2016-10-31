package com.shore.tuttopazzo.movierandom.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

/**
 * Created by TuttoPazzo$$ on 15-Oct-16.
 */
public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void deleteDatabase(){
        mContext.deleteDatabase(MovieDBHellper.DATABASE_NAME);
    }

    public void TestCreateDb() throws Throwable{

        final HashSet<String> tableNameHashSet= new HashSet<>();

        tableNameHashSet.add(MovieDataContract.MovieEntry.TABLE_NAME);

        mContext.deleteDatabase(MovieDBHellper.DATABASE_NAME);
        SQLiteDatabase db = new MovieDBHellper(this.mContext).getWritableDatabase();

        assertTrue("Error getting Writable DB",db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error creating table",c.moveToFirst());

        int columnIndex=c.getColumnIndex("name");
        do{
            String tabelName=c.getString(columnIndex);
            tableNameHashSet.remove(tabelName);
        }while (c.moveToNext());

        c = db.rawQuery("PRAGMA table_info(" + MovieDataContract.MovieEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> locationColumnHashSet = new HashSet<String>();
        locationColumnHashSet.add(MovieDataContract.MovieEntry._ID);
        locationColumnHashSet.add(MovieDataContract.MovieEntry.COLUMN_MOVIE);
        locationColumnHashSet.add(MovieDataContract.MovieEntry.COLUMN_GENRE);


        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            locationColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required location entry columns",
                locationColumnHashSet.isEmpty());
        db.close();

    }


    public void testBasicMovieQuery() {


        mContext.deleteDatabase(MovieDBHellper.DATABASE_NAME);


        MovieDBHellper dbHelper = new MovieDBHellper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(MovieDataContract.MovieEntry.COLUMN_MOVIE, "WallE");
        testValues.put(MovieDataContract.MovieEntry.COLUMN_GENRE, "anime");



        long weatherRowId = db.insert(MovieDataContract.MovieEntry.TABLE_NAME,null,testValues);;
        assertTrue("Unable to Insert WeatherEntry into the Database", weatherRowId != -1);

        db.close();

        // Test the basic content provider query
        Cursor cursor = mContext.getContentResolver().query(
                MovieDataContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        // Make sure we get the correct cursor out of the database
        TestUtilities.validateCursor("testBasicWeatherQuery", cursor, testValues);
    }





}
