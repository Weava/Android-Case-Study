package com.target.dealbrowserpoc;

import android.app.Application;

import com.target.dealbrowserpoc.di.DaggerAppComponent;

/**
 * {@link Application} instance for setting up any app level dependencies.
 */
public class DealBrowserApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DiInjector.INSTANCE.init(DaggerAppComponent.create());
    }
}
