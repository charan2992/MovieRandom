package com.shore.tuttopazzo.movierandom.sync;

import android.test.AndroidTestCase;

/**
 * Created by TuttoPazzo$$ on 30-Oct-16.
 */
public class SyncAdapterTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAPI(){

        new FetchMovieDataSyncAdapter().onSync();

       // assertTrue("JSON invalid",JSON);
    }
}
