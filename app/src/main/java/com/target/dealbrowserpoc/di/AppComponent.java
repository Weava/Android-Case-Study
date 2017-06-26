package com.target.dealbrowserpoc.di;

import com.target.dealbrowserpoc.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component defining app wide graph.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
