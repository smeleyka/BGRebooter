package ru.smeleyka.bgrebooter.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


public class ContextModule {

    public Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    public Context context() {
        return context.getApplicationContext();
    }
}

