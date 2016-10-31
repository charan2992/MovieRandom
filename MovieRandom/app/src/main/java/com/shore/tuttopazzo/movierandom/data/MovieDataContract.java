package com.shore.tuttopazzo.movierandom.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by TuttoPazzo$$ on 08-Oct-16.
 */
public class MovieDataContract {

    public static final String CONTENT_AUTHORITY="com.shore.tuttopazzo.movierandom";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE="movie";

    public static final class MovieEntry implements BaseColumns{


        //content://com.shore.tuttopazzo.movierandom/movie
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        //vnd.android.cursor.dir/com.shore.tuttopazzo.movierandom/movie
        public static final String CONTENT_TYPE =ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        //vnd.android.cursor.item/com.shore.tuttopazzo.movierandom/movie
        public static final String CONTENT_ITEM_TYPE =ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_MOVIE="movie";
        public static final String COLUMN_GENRE="genre";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //content://com.shore.tuttopazzo.movierandom/movie/genre
        public static Uri buildMovieGenreUri(String genre) {

            Uri uri=CONTENT_URI.buildUpon().appendPath(genre).build();
            Log.i("charan"," buildMovieGenreUri"+uri.toString());


            return uri;
        }

        public static String getGenreFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }
}
