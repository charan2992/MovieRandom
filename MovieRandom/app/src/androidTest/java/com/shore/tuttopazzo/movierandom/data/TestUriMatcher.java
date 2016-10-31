package com.shore.tuttopazzo.movierandom.data;

/**
 * Created by TuttoPazzo$$ on 16-Oct-16.
 */
/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.UriMatcher;
import android.net.Uri;
import android.test.AndroidTestCase;





public class TestUriMatcher extends AndroidTestCase {



    private static final Uri TEST_MOVIE_DIR = MovieDataContract.MovieEntry.CONTENT_URI;
    ;


    /*
        Students: This function tests that your UriMatcher returns the correct integer value
        for each of the Uri types that our ContentProvider can handle.  Uncomment this when you are
        ready to test your UriMatcher.
     */
    public void testUriMatcher() {
        UriMatcher testMatcher = MovieDataProvider.buildUriMatcher();

        assertEquals("Error: The  URI was matched incorrectly.",
                testMatcher.match(TEST_MOVIE_DIR), MovieDataProvider.MOVIE);


    }
}

