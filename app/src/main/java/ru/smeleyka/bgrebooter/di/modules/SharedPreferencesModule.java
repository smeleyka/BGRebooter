package ru.smeleyka.bgrebooter.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ru.smeleyka.bgrebooter.di.ApplicationContext;
import ru.smeleyka.bgrebooter.di.RandomUserApplicationScope;
import ru.smeleyka.bgrebooter.model.data.DataManager;
import ru.smeleyka.bgrebooter.model.data.SharedPrefsHelper;

@Module (includes = ContextModule.class)
public class SharedPreferencesModule {

//    public Context context;
//
//
//    public SharedPreferencesModule(Context context) {
//        this.context = context;
//    }

    @Provides
    public SharedPreferences getSharedPreferences(@ApplicationContext Context context) {
        System.out.println("CREATING SHARED PREFERENCES");
        return context.getSharedPreferences("auth_key", Context.MODE_PRIVATE);
    }

    @Provides
    public SharedPrefsHelper getSharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelper(sharedPreferences);
    }

    @Provides
    public DataManager dataManager(SharedPrefsHelper sharedPrefsHelper) {
        return new DataManager(sharedPrefsHelper);
    }


}
