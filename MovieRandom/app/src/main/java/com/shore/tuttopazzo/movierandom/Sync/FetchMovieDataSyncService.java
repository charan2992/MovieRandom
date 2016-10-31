package com.shore.tuttopazzo.movierandom.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by TuttoPazzo$$ on 08-Oct-16.
 */
public class FetchMovieDataSyncService  extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static FetchMovieDataSyncAdapter SFetchMovieDataSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("FetchMovieService", "onCreate - FetchMovieDataSyncService");
        synchronized (sSyncAdapterLock) {
            if (SFetchMovieDataSyncAdapter == null) {
                SFetchMovieDataSyncAdapter = new FetchMovieDataSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return SFetchMovieDataSyncAdapter.getSyncAdapterBinder();
    }
}
