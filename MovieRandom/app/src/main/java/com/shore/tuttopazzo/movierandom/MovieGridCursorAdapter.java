package com.shore.tuttopazzo.movierandom;

import android.content.Context;
import android.database.Cursor;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by TuttoPazzo$$ on 15-Oct-16.
 */
public class MovieGridCursorAdapter extends CursorAdapter {
    public MovieGridCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {



        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);

        Log.i("charan","inflating in newview" +cursor.getPosition());
      // ViewHolder viewHolder = new ViewHolder(view);
       // view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView gridText=(TextView) view.findViewById(R.id.grid_item_label);



        String movieText=cursor.getString(MovieGridFragment.COL_MOVIE);
        Log.i("charan","setting text in bind view + for " + cursor.getPosition()+" movieText: " + movieText);
        gridText.setText(movieText);

    }





   /* @Override
    public int getViewTypeCount() {
        return 10;
    }*/
}
