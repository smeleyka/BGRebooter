package ru.smeleyka.bgrebooter;

import android.app.Application;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.di.AppComponent;
import ru.smeleyka.bgrebooter.di.DaggerAppComponent;
import ru.smeleyka.bgrebooter.di.modules.ContextModule;
import ru.smeleyka.bgrebooter.di.modules.SharedPreferencesModule;

public class App extends Application{

    private static App instance;
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

