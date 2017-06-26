package com.target.dealbrowserpoc;

import com.target.dealbrowserpoc.di.AppComponent;

/**
 * Singleton instance utility method for injecting dagger providers.
 */
public enum DiInjector {
    INSTANCE;

    private AppComponent appComponent;

    public AppComponent getAppGraph() {
        return appComponent;
    }

    void init(AppComponent graph) {
        if (graph == null) {
            return;
        }

        appComponent = graph;
    }
}
