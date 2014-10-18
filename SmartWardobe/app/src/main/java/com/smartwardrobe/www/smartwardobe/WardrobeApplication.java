package com.smartwardrobe.www.smartwardobe;

import com.activeandroid.ActiveAndroid;

import java.util.HashMap;

/**
 * Created by eugene on 18.10.14.
 */
public class WardrobeApplication  extends com.activeandroid.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
