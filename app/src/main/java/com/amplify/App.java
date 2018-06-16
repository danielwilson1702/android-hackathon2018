/**
 * ****************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package com.amplify;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.amplify.utils.Constants;
import com.amplify.utils.RandomString;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class App extends Application {
    private static final String TAG = "Application";
    private static App sApp;
    private static String mCloverToken;
    private static RandomString mRandomString = new RandomString(16);
    private AtomicInteger counter = new AtomicInteger();
    private Random mRandom = new Random();

    public static App getInstance() {
        return sApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getInstance());
    }

    public static String getApiKey() {
        return getSharedPreferences().getString(Constants.Prefs.API_KEY, null);
    }

    public static void dismissAllKeyboards(Activity a, final EditText[] editTextArray) {
        InputMethodManager imm = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        for (EditText e : editTextArray) {
            if(imm != null && e != null && e.getWindowToken() != null)
                imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
        }
    }

    public static String getRandomString() {
        return mRandomString.nextString();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //if (LeakCanary.isInAnalyzerProcess(this)) {
            //return;
        //}

        sApp = this;

        //JodaTimeAndroid.init(this);

        //JobManager.create(this).addJobCreator(new AsyncJobCreator());

        //LeakCanary.install(this);

        //Stetho.initializeWithDefaults(this);
    }

    public int getRandomInt(int min, int max) {
        return mRandom.nextInt((max - min) + 1) + min;
    }

    public int getNextUniqueIndex() {
        return counter.getAndIncrement();
    }

}
