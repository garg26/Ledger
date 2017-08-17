package com.example.kartikeya.ledger;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import simplifii.framework.utility.Preferences;



public class AppController extends MultiDexApplication {
    private static AppController instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Preferences.initSharedPreferences(this);
        MultiDex.install(this);
    }

    public static AppController getInstance(){
        return instance;
    }
}
