package com.shore.tuttopazzo.movierandom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.shore.tuttopazzo.movierandom.sync.FetchMovieDataSyncAdapter;
import com.shore.tuttopazzo.movierandom.data.MovieDBHellper;
import com.shore.tuttopazzo.movierandom.data.MovieDataContract;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * { MovieGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {



    private MovieGridCursorAdapter movieAdapter;
    private String genreSelected;

    private static final String[] MOVIE_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            MovieDataContract.MovieEntry._ID,
            MovieDataContract.MovieEntry.COLUMN_MOVIE,
            MovieDataContract.MovieEntry.COLUMN_GENRE

    };
    static final int _ID = 0;
    static final int COL_MOVIE = 1;
    static final int COL_GENRE = 2;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView textView;
    private GridView movieGrid;
   // private OnFragmentInteractionListener mListener;

    public MovieGridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieGridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieGridFragment newInstance(String param1, String param2) {
        MovieGridFragment fragment = new MovieGridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("charan","on Create MovieGrodFragment");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        genreSelected=mParam1;

        SQLiteDatabase db= new MovieDBHellper(getActivity()).getWritableDatabase();

        db.close();


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_list, container, false);
        textView=(TextView)rootView.findViewById(R.id.fragText);
        movieGrid=(GridView) rootView.findViewById(R.id.movieGrid);

        movieAdapter=new MovieGridCursorAdapter(getActivity(),null);
       // movieGrid.setAdapter(new MovieGridAdapter(getActivity()));
        movieGrid.setAdapter(movieAdapter);


        //textView.setText(mParam1);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

       String sortOrder = MovieDataContract.MovieEntry.COLUMN_MOVIE + " ASC";

        Uri movieDataUri = MovieDataContract.MovieEntry.buildMovieGenreUri(genreSelected);
          //Uri movieDataUri=MovieDataContract.MovieEntry.CONTENT_URI;


        return new CursorLoader(getActivity(),
                movieDataUri,
                MOVIE_COLUMNS,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
     movieAdapter.swapCursor(data);
      //  Log.i("charan","load finished "+data.moveToNext());
        movieGrid.setAdapter(movieAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        movieAdapter.swapCursor(null);
    }

    // TODO: Rename method, update argument and hook method into UI event
/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


       ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
