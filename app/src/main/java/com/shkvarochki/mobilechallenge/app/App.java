package com.shkvarochki.mobilechallenge.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import org.androidannotations.annotations.EApplication;

@EApplication
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
