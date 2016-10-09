package com.shore.tuttopazzo.movierandom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by TuttoPazzo$$ on 08-Oct-16.
 */
public class MovieGridAdapter extends BaseAdapter {

   private  Context context;
    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    public MovieGridAdapter(Context context) {
       this.context=context;
    }

    @Override
    public int getCount() {
        return numbers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridItemLayout;

        if (convertView == null) {

            gridItemLayout = new View(context);
            gridItemLayout=inflater.inflate(R.layout.movie_list_item,null);

            TextView gridText=(TextView)gridItemLayout.findViewById(R.id.grid_item_label);
            gridText.setText(numbers[position]);

        }else{
            gridItemLayout=convertView;
        }

        return gridItemLayout;
    }
}
