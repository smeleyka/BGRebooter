package ru.smeleyka.bgrebooter.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.smeleyka.bgrebooter.di.ApplicationContext;
import ru.smeleyka.bgrebooter.di.RandomUserApplicationScope;


@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}


