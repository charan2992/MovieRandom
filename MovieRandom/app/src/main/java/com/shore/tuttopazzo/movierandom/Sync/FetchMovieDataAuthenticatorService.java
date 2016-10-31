package com.shore.tuttopazzo.movierandom.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by TuttoPazzo$$ on 30-Oct-16.
 */
public class FetchMovieDataAuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private FetchMovieDataAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new FetchMovieDataAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}

