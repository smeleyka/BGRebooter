package ru.smeleyka.bgrebooter;

import android.app.Application;

import ru.smeleyka.bgrebooter.di.AppComponent;
import ru.smeleyka.bgrebooter.di.DaggerAppComponent;

public class App extends Application{

    private static App instance;
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

